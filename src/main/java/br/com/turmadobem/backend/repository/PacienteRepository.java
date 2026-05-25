package br.com.turmadobem.backend.repository;

import br.com.turmadobem.backend.exception.NotFoundException;
import br.com.turmadobem.backend.model.Paciente;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PacienteRepository extends BaseRepository<Paciente> {
    public PacienteRepository() {
        super(Paciente.class, "Paciente");
    }

    public Paciente findByUsuarioId(Long usuarioId) {
        Paciente paciente = em.find(Paciente.class, usuarioId);
        if (paciente == null) throw new NotFoundException("Paciente", usuarioId);
        return paciente;
    }
}
