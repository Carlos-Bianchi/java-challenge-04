package br.com.turmadobem.backend.service;

import br.com.turmadobem.backend.dto.AgendamentoRequest;
import br.com.turmadobem.backend.exception.BusinessException;
import br.com.turmadobem.backend.model.Agendamento;
import br.com.turmadobem.backend.model.StatusAgendamento;
import br.com.turmadobem.backend.model.StatusMatch;
import br.com.turmadobem.backend.repository.AgendamentoRepository;
import br.com.turmadobem.backend.repository.MatchRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class AgendamentoService {
    private final AgendamentoRepository agendamentos;
    private final MatchRepository matches;

    public AgendamentoService(AgendamentoRepository agendamentos, MatchRepository matches) {
        this.agendamentos = agendamentos;
        this.matches = matches;
    }

    public List<Agendamento> listAll() { return agendamentos.listAll(); }
    public Agendamento findById(Long id) { return agendamentos.findById(id); }

    @Transactional
    public Agendamento create(AgendamentoRequest request) {
        Agendamento agendamento = new Agendamento();
        apply(agendamento, request);
        return agendamentos.save(agendamento);
    }

    @Transactional
    public Agendamento update(Long id, AgendamentoRequest request) {
        Agendamento agendamento = agendamentos.findById(id);
        apply(agendamento, request);
        return agendamento;
    }

    @Transactional
    public void delete(Long id) { agendamentos.delete(agendamentos.findById(id)); }

    private void apply(Agendamento agendamento, AgendamentoRequest request) {
        agendamento.match = matches.findById(request.matchId());
        if (agendamento.match.status != StatusMatch.confirmado) {
            throw new BusinessException(Response.Status.CONFLICT, "match_not_confirmed", "Agendamento exige match confirmado");
        }
        agendamento.dataAgendada = request.dataAgendada();
        agendamento.turno = request.turno();
        agendamento.status = request.status() == null ? StatusAgendamento.agendada : request.status();
        agendamento.observacoes = request.observacoes();
    }
}
