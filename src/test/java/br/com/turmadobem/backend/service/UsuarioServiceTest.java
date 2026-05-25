package br.com.turmadobem.backend.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import br.com.turmadobem.backend.dto.EnderecoRequest;
import br.com.turmadobem.backend.dto.UsuarioRequest;
import br.com.turmadobem.backend.exception.BusinessException;
import br.com.turmadobem.backend.model.PapelUsuario;
import br.com.turmadobem.backend.repository.DentistaRepository;
import br.com.turmadobem.backend.repository.EnderecoRepository;
import br.com.turmadobem.backend.repository.EspecialidadeRepository;
import br.com.turmadobem.backend.repository.PacienteRepository;
import br.com.turmadobem.backend.repository.UsuarioRepository;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class UsuarioServiceTest {
    @Test
    void rejectsDuplicateEmailOnRegistration() {
        UsuarioRepository usuarios = mock(UsuarioRepository.class);
        when(usuarios.existsByEmail("ana@example.com")).thenReturn(true);
        UsuarioService service = new UsuarioService(usuarios, mock(EnderecoRepository.class), mock(EspecialidadeRepository.class),
                mock(PacienteRepository.class), mock(DentistaRepository.class), new PasswordService());
        UsuarioRequest request = new UsuarioRequest("Ana", "ana@example.com", "segredo123", PapelUsuario.paciente,
                null, null, "12345678901", null, null, null, null, null, null, null, null, null, null, null);

        assertThatThrownBy(() -> service.registerUser(request))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("Email");
    }

    @Test
    void rejectsPatientRegistrationWithoutSpecialty() {
        UsuarioRepository usuarios = mock(UsuarioRepository.class);
        when(usuarios.existsByEmail("ana@example.com")).thenReturn(false);
        when(usuarios.existsByCpf("12345678901")).thenReturn(false);
        UsuarioService service = new UsuarioService(usuarios, mock(EnderecoRepository.class), mock(EspecialidadeRepository.class),
                mock(PacienteRepository.class), mock(DentistaRepository.class), new PasswordService());
        UsuarioRequest request = new UsuarioRequest(
                "Ana",
                "ana@example.com",
                "segredo123",
                PapelUsuario.paciente,
                null,
                "11999999999",
                "12345678901",
                LocalDate.of(1999, 1, 1),
                new EnderecoRequest("12345678", "Rua A", "10", null, "Centro", "São Paulo", "SP"),
                null,
                null,
                "Necessita avaliação",
                true,
                null,
                null,
                null,
                null,
                null
        );

        assertThatThrownBy(() -> service.registerUser(request))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("Especialidade");
    }
}
