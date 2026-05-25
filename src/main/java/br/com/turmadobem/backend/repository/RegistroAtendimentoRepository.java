package br.com.turmadobem.backend.repository;

import br.com.turmadobem.backend.model.RegistroAtendimento;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RegistroAtendimentoRepository extends BaseRepository<RegistroAtendimento> {
    public RegistroAtendimentoRepository() {
        super(RegistroAtendimento.class, "Registro de atendimento");
    }
}
