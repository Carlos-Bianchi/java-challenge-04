package br.com.turmadobem.backend.dto;

import br.com.turmadobem.backend.model.StatusUsuario;
import jakarta.validation.constraints.NotNull;

public record StatusUsuarioRequest(@NotNull StatusUsuario status) {
}
