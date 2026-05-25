package br.com.turmadobem.backend.dto;

import java.math.BigDecimal;

public record DentistRecommendationResponse(Long dentistaUsuarioId, String nomeCompleto, String especialidade,
                                            BigDecimal percentualCompatibilidade, short pontuacaoLocalizacao,
                                            short pontuacaoEspecialidade) {
}
