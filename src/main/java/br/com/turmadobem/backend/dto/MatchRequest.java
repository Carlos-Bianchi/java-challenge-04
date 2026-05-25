package br.com.turmadobem.backend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record MatchRequest(@NotNull Long pacienteUsuarioId, @NotNull Long dentistaUsuarioId,
                           @Min(0) @Max(100) BigDecimal percentualCompatibilidade,
                           @Min(0) @Max(100) Short pontuacaoLocalizacao,
                           @Min(0) @Max(100) Short pontuacaoEspecialidade,
                           String observacoes) {
}
