package br.com.turmadobem.backend.dto;

import br.com.turmadobem.backend.model.Especialidade;

public record EspecialidadeResponse(Long id, String nome, String descricao) {
    public static EspecialidadeResponse from(Especialidade especialidade) {
        return new EspecialidadeResponse(especialidade.id, especialidade.nome, especialidade.descricao);
    }
}
