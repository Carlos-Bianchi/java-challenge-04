package br.com.turmadobem.backend.dto;

import br.com.turmadobem.backend.model.StatusAgendamento;
import br.com.turmadobem.backend.model.TurnoDisponibilidade;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record AgendamentoRequest(@NotNull Long matchId, @NotNull @FutureOrPresent LocalDate dataAgendada,
                                 @NotNull TurnoDisponibilidade turno, StatusAgendamento status, String observacoes) {
}
