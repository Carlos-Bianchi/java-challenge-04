package br.com.turmadobem.backend.dto;

import br.com.turmadobem.backend.model.Match;
import br.com.turmadobem.backend.model.StatusMatch;
import java.math.BigDecimal;

public record MatchResponse(Long id, Long pacienteUsuarioId, Long dentistaUsuarioId, BigDecimal percentualCompatibilidade,
                            Short pontuacaoLocalizacao, Short pontuacaoEspecialidade, StatusMatch status, String observacoes) {
    public static MatchResponse from(Match match) {
        return new MatchResponse(match.id, match.paciente.usuarioId, match.dentista.usuarioId, match.percentualCompatibilidade,
                match.pontuacaoLocalizacao, match.pontuacaoEspecialidade, match.status, match.observacoes);
    }
}
