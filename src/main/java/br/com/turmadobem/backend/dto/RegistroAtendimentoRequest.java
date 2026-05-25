package br.com.turmadobem.backend.dto;

import br.com.turmadobem.backend.model.GravidadeCondicao;
import br.com.turmadobem.backend.model.StatusTratamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record RegistroAtendimentoRequest(@NotNull Long agendamentoId, @NotNull LocalDate dataAtendimento,
                                         @NotBlank @Size(max = 120) String procedimento,
                                         @NotBlank @Size(max = 120) String condicaoPaciente,
                                         @NotNull GravidadeCondicao gravidade, String observacoes,
                                         LocalDate proximaConsulta, StatusTratamento status) {
}
