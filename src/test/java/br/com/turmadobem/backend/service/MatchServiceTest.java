package br.com.turmadobem.backend.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.turmadobem.backend.dto.MatchRequest;
import br.com.turmadobem.backend.exception.BusinessException;
import br.com.turmadobem.backend.model.DentistaVoluntario;
import br.com.turmadobem.backend.model.Match;
import br.com.turmadobem.backend.model.Paciente;
import br.com.turmadobem.backend.model.StatusMatch;
import br.com.turmadobem.backend.repository.DentistaRepository;
import br.com.turmadobem.backend.repository.MatchRepository;
import br.com.turmadobem.backend.repository.PacienteRepository;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class MatchServiceTest {
    @Test
    void rejectsFinalizedMatchStatusTransition() {
        MatchRepository repository = mock(MatchRepository.class);
        Match match = new Match();
        match.id = 10L;
        match.status = StatusMatch.confirmado;
        when(repository.findById(10L)).thenReturn(match);

        MatchService service = new MatchService(repository, mock(PacienteRepository.class), mock(DentistaRepository.class));

        assertThatThrownBy(() -> service.updateMatchStatus(10L, StatusMatch.cancelado))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("finalizados");
    }

    @Test
    void setsRequestedAtWhenCreatingMatch() {
        MatchRepository repository = mock(MatchRepository.class);
        PacienteRepository pacientes = mock(PacienteRepository.class);
        DentistaRepository dentistas = mock(DentistaRepository.class);
        Paciente paciente = new Paciente();
        DentistaVoluntario dentista = new DentistaVoluntario();
        when(pacientes.findByUsuarioId(1L)).thenReturn(paciente);
        when(dentistas.findById(11L)).thenReturn(dentista);
        when(repository.save(any(Match.class))).thenAnswer(invocation -> invocation.getArgument(0));
        MatchService service = new MatchService(repository, pacientes, dentistas);

        Match created = service.create(new MatchRequest(1L, 11L, BigDecimal.valueOf(95), (short) 30, (short) 65, "Observação"));

        assertThat(created.solicitadoEm).isNotNull();
        assertThat(created.status).isEqualTo(StatusMatch.pendente);
        verify(repository).save(created);
    }
}
