package br.com.turmadobem.backend.dto;

import br.com.turmadobem.backend.model.GravidadeCondicao;
import br.com.turmadobem.backend.model.RegistroAtendimento;
import br.com.turmadobem.backend.model.StatusTratamento;
import java.time.LocalDate;

public record RegistroAtendimentoResponse(Long id, Long agendamentoId, LocalDate dataAtendimento, String procedimento,
                                          String condicaoPaciente, GravidadeCondicao gravidade, String observacoes,
                                          LocalDate proximaConsulta, StatusTratamento status) {
    public static RegistroAtendimentoResponse from(RegistroAtendimento registro) {
        return new RegistroAtendimentoResponse(registro.id, registro.agendamento.id, registro.dataAtendimento,
                registro.procedimento, registro.condicaoPaciente, registro.gravidade, registro.observacoes,
                registro.proximaConsulta, registro.status);
    }
}
