package br.com.turmadobem.backend.dto;

import br.com.turmadobem.backend.model.PapelUsuario;
import br.com.turmadobem.backend.model.StatusUsuario;
import br.com.turmadobem.backend.model.Usuario;
import java.time.LocalDate;

public record UsuarioResponse(Long id, String nomeCompleto, String email, PapelUsuario papel, StatusUsuario status,
                              String telefone, String cpf, LocalDate dataNascimento) {
    public static UsuarioResponse from(Usuario usuario) {
        return new UsuarioResponse(usuario.id, usuario.nomeCompleto, usuario.email, usuario.papel, usuario.status,
                usuario.telefone, usuario.cpf, usuario.dataNascimento);
    }
}
