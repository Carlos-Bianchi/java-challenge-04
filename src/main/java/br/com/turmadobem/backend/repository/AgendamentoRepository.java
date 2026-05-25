package br.com.turmadobem.backend.repository;

import br.com.turmadobem.backend.model.Agendamento;
import br.com.turmadobem.backend.model.StatusAgendamento;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AgendamentoRepository extends BaseRepository<Agendamento> {
    public AgendamentoRepository() {
        super(Agendamento.class, "Agendamento");
    }

    public long countByStatus(StatusAgendamento status) {
        return em.createQuery("select count(a) from Agendamento a where a.status = :status", Long.class)
                .setParameter("status", status)
                .getSingleResult();
    }
}
