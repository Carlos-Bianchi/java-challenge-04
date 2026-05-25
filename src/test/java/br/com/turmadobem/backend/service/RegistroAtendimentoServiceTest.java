package br.com.turmadobem.backend.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import br.com.turmadobem.backend.dto.RegistroAtendimentoRequest;
import br.com.turmadobem.backend.exception.BusinessException;
import br.com.turmadobem.backend.model.Agendamento;
import br.com.turmadobem.backend.model.GravidadeCondicao;
import br.com.turmadobem.backend.model.Match;
import br.com.turmadobem.backend.model.StatusAgendamento;
import br.com.turmadobem.backend.model.StatusMatch;
import br.com.turmadobem.backend.repository.AgendamentoRepository;
import br.com.turmadobem.backend.repository.RegistroAtendimentoRepository;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class RegistroAtendimentoServiceTest {
    @Test
    void rejectsAttendanceForUnconfirmedMatch() {
        AgendamentoRepository agendamentos = mock(AgendamentoRepository.class);
        Agendamento agendamento = new Agendamento();
        agendamento.id = 2L;
        agendamento.status = StatusAgendamento.agendada;
        agendamento.match = new Match();
        agendamento.match.status = StatusMatch.pendente;
        when(agendamentos.findById(2L)).thenReturn(agendamento);
        RegistroAtendimentoService service = new RegistroAtendimentoService(mock(RegistroAtendimentoRepository.class), agendamentos);

        RegistroAtendimentoRequest request = new RegistroAtendimentoRequest(2L, LocalDate.now(), "Avaliação", null,
                GravidadeCondicao.leve, null, null, null);

        assertThatThrownBy(() -> service.recordAttendance(request))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("match confirmado");
    }
}
