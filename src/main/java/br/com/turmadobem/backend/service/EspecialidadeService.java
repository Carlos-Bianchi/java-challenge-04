package br.com.turmadobem.backend.service;

import br.com.turmadobem.backend.model.Especialidade;
import br.com.turmadobem.backend.repository.EspecialidadeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class EspecialidadeService {
    private final EspecialidadeRepository especialidades;

    public EspecialidadeService(EspecialidadeRepository especialidades) {
        this.especialidades = especialidades;
    }

    public List<Especialidade> listAll() {
        return especialidades.listAll();
    }
}
