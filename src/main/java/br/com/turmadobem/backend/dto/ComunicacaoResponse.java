package br.com.turmadobem.backend.dto;

import br.com.turmadobem.backend.model.CanalComunicacao;
import br.com.turmadobem.backend.model.Comunicacao;

public record ComunicacaoResponse(Long id, Long matchId, Long remetenteUsuarioId, CanalComunicacao canal,
                                  String categoria, String assunto, String resumo, String conteudo,
                                  Boolean urgente, Boolean lida) {
    public static ComunicacaoResponse from(Comunicacao comunicacao) {
        return new ComunicacaoResponse(comunicacao.id, comunicacao.match == null ? null : comunicacao.match.id,
                comunicacao.remetente == null ? null : comunicacao.remetente.id, comunicacao.canal, comunicacao.categoria,
                comunicacao.assunto, comunicacao.resumo, comunicacao.conteudo, comunicacao.urgente, comunicacao.lida);
    }
}
