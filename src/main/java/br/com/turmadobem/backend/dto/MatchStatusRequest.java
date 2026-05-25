package br.com.turmadobem.backend.dto;

import br.com.turmadobem.backend.model.StatusMatch;
import jakarta.validation.constraints.NotNull;

public record MatchStatusRequest(@NotNull StatusMatch status) {
}
