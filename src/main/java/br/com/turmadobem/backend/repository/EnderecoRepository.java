package br.com.turmadobem.backend.repository;

import br.com.turmadobem.backend.model.EnderecoUsuario;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnderecoRepository extends BaseRepository<EnderecoUsuario> {
    public EnderecoRepository() {
        super(EnderecoUsuario.class, "Endereço");
    }
}
