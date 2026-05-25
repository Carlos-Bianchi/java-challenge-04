package br.com.turmadobem.backend.dto;

import br.com.turmadobem.backend.model.PapelUsuario;
import br.com.turmadobem.backend.model.PrioridadeClinica;
import br.com.turmadobem.backend.model.StatusUsuario;
import br.com.turmadobem.backend.model.TurnoDisponibilidade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record UsuarioRequest(
        @NotBlank @Size(max = 150) String nomeCompleto,
        @NotBlank @Email @Size(max = 255) String email,
        @NotBlank @Size(min = 6, max = 80) String senha,
        @NotNull PapelUsuario papel,
        StatusUsuario status,
        @NotBlank @Size(max = 20) String telefone,
        @NotBlank @Pattern(regexp = "\\d{11}") String cpf,
        @NotNull LocalDate dataNascimento,
        @Valid EnderecoRequest endereco,
        Long especialidadeId,
        PrioridadeClinica prioridade,
        String descricaoNecessidade,
        Boolean aceitaDeslocamento,
        String cro,
        String nomeClinica,
        TurnoDisponibilidade turnoPreferencial,
        Boolean aceitaNovosPacientes,
        String notaDisponibilidade) {
}
