package br.com.turmadobem.backend.dto;

import br.com.turmadobem.backend.model.Agendamento;
import br.com.turmadobem.backend.model.StatusAgendamento;
import br.com.turmadobem.backend.model.TurnoDisponibilidade;
import java.time.LocalDate;

public record AgendamentoResponse(Long id, Long matchId, LocalDate dataAgendada, TurnoDisponibilidade turno,
                                  StatusAgendamento status, String observacoes) {
    public static AgendamentoResponse from(Agendamento agendamento) {
        return new AgendamentoResponse(agendamento.id, agendamento.match.id, agendamento.dataAgendada,
                agendamento.turno, agendamento.status, agendamento.observacoes);
    }
}
