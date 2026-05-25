package br.com.turmadobem.backend.service;

import br.com.turmadobem.backend.dto.DentistRecommendationResponse;
import br.com.turmadobem.backend.dto.MatchRequest;
import br.com.turmadobem.backend.exception.BusinessException;
import br.com.turmadobem.backend.model.DentistaVoluntario;
import br.com.turmadobem.backend.model.Match;
import br.com.turmadobem.backend.model.Paciente;
import br.com.turmadobem.backend.model.StatusMatch;
import br.com.turmadobem.backend.repository.DentistaRepository;
import br.com.turmadobem.backend.repository.MatchRepository;
import br.com.turmadobem.backend.repository.PacienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@ApplicationScoped
public class MatchService {
    private final MatchRepository matches;
    private final PacienteRepository pacientes;
    private final DentistaRepository dentistas;

    public MatchService(MatchRepository matches, PacienteRepository pacientes, DentistaRepository dentistas) {
        this.matches = matches;
        this.pacientes = pacientes;
        this.dentistas = dentistas;
    }

    public List<Match> listAll() {
        return matches.listAll();
    }

    public Match findById(Long id) {
        return matches.findById(id);
    }

    public List<DentistRecommendationResponse> recommendDentistsForPatient(Long patientId) {
        Paciente paciente = pacientes.findByUsuarioId(patientId);
        return dentistas.listAvailable().stream()
                .map(dentista -> recommendation(paciente, dentista))
                .sorted(Comparator.comparing(DentistRecommendationResponse::percentualCompatibilidade).reversed())
                .toList();
    }

    @Transactional
    public Match create(MatchRequest request) {
        Match match = new Match();
        match.paciente = pacientes.findByUsuarioId(request.pacienteUsuarioId());
        match.dentista = dentistas.findById(request.dentistaUsuarioId());
        match.percentualCompatibilidade = request.percentualCompatibilidade() == null ? BigDecimal.ZERO : request.percentualCompatibilidade();
        match.pontuacaoLocalizacao = request.pontuacaoLocalizacao() == null ? 0 : request.pontuacaoLocalizacao();
        match.pontuacaoEspecialidade = request.pontuacaoEspecialidade() == null ? 0 : request.pontuacaoEspecialidade();
        match.status = StatusMatch.pendente;
        match.solicitadoEm = LocalDateTime.now();
        match.observacoes = request.observacoes();
        return matches.save(match);
    }

    @Transactional
    public Match updateMatchStatus(Long matchId, StatusMatch status) {
        Match match = matches.findById(matchId);
        if (match.status == StatusMatch.cancelado || match.status == StatusMatch.confirmado) {
            throw new BusinessException(Response.Status.CONFLICT, "invalid_match_transition", "Matches finalizados não podem mudar de status");
        }
        if (status == StatusMatch.pendente) {
            throw new BusinessException(Response.Status.BAD_REQUEST, "invalid_match_transition", "Status pendente não é uma transição válida");
        }
        match.status = status;
        match.respondidoEm = LocalDateTime.now();
        return match;
    }

    @Transactional
    public void delete(Long id) {
        matches.delete(matches.findById(id));
    }

    private DentistRecommendationResponse recommendation(Paciente paciente, DentistaVoluntario dentista) {
        boolean sameSpecialty = paciente.especialidadeNecessaria != null && dentista.especialidadePrincipal != null
                && paciente.especialidadeNecessaria.id.equals(dentista.especialidadePrincipal.id);
        short specialty = (short) (sameSpecialty ? 70 : 25);
        short location = (short) (Boolean.TRUE.equals(paciente.aceitaDeslocamento) ? 30 : 15);
        BigDecimal percent = BigDecimal.valueOf(specialty + location).min(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP);
        String specialtyName = dentista.especialidadePrincipal == null ? null : dentista.especialidadePrincipal.nome;
        return new DentistRecommendationResponse(dentista.usuarioId, dentista.usuario.nomeCompleto, specialtyName, percent, location, specialty);
    }
}
