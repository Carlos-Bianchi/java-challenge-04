package br.com.turmadobem.backend.service;

import br.com.turmadobem.backend.dto.DashboardSummaryResponse;
import br.com.turmadobem.backend.model.PapelUsuario;
import br.com.turmadobem.backend.model.StatusAgendamento;
import br.com.turmadobem.backend.model.StatusMatch;
import br.com.turmadobem.backend.repository.AgendamentoRepository;
import br.com.turmadobem.backend.repository.ComunicacaoRepository;
import br.com.turmadobem.backend.repository.MatchRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class DashboardService {
    @PersistenceContext
    EntityManager em;

    private final MatchRepository matches;
    private final ComunicacaoRepository comunicacoes;
    private final AgendamentoRepository agendamentos;

    public DashboardService(MatchRepository matches, ComunicacaoRepository comunicacoes, AgendamentoRepository agendamentos) {
        this.matches = matches;
        this.comunicacoes = comunicacoes;
        this.agendamentos = agendamentos;
    }

    public DashboardSummaryResponse buildDashboardSummary() {
        long totalUsuarios = em.createQuery("select count(u) from Usuario u", Long.class).getSingleResult();
        Map<String, Long> usuariosPorPapel = Arrays.stream(PapelUsuario.values())
                .collect(Collectors.toMap(Enum::name, papel -> em.createQuery("select count(u) from Usuario u where u.papel = :papel", Long.class)
                        .setParameter("papel", papel).getSingleResult()));
        Map<String, Long> agendamentosPorStatus = Arrays.stream(StatusAgendamento.values())
                .collect(Collectors.toMap(Enum::name, agendamentos::countByStatus));
        return new DashboardSummaryResponse(totalUsuarios, usuariosPorPapel, matches.countByStatus(StatusMatch.confirmado),
                comunicacoes.countUnread(), agendamentosPorStatus);
    }
}
