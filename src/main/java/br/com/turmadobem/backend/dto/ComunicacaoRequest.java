package br.com.turmadobem.backend.dto;

import br.com.turmadobem.backend.model.CanalComunicacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ComunicacaoRequest(Long matchId, @NotNull Long remetenteUsuarioId, @NotNull CanalComunicacao canal,
                                 @NotBlank @Size(max = 80) String categoria,
                                 @NotBlank @Size(max = 180) String assunto,
                                 @NotBlank @Size(max = 255) String resumo,
                                 @NotBlank String conteudo, Boolean urgente) {
}
