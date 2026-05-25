package br.com.turmadobem.backend.model;

import java.io.Serializable;
import java.util.Objects;

public class UsuarioPermissaoId implements Serializable {
    public Long usuario;
    public Long permissao;

    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof UsuarioPermissaoId id)) return false;
        return Objects.equals(usuario, id.usuario) && Objects.equals(permissao, id.permissao);
    }

    public int hashCode() {
        return Objects.hash(usuario, permissao);
    }
}
