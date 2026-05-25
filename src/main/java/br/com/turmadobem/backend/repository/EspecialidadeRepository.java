package br.com.turmadobem.backend.repository;

import br.com.turmadobem.backend.model.Especialidade;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EspecialidadeRepository extends BaseRepository<Especialidade> {
    public EspecialidadeRepository() {
        super(Especialidade.class, "Especialidade");
    }
}
