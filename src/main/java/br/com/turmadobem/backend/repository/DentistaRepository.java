package br.com.turmadobem.backend.repository;

import br.com.turmadobem.backend.model.DentistaVoluntario;
import br.com.turmadobem.backend.model.StatusUsuario;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class DentistaRepository extends BaseRepository<DentistaVoluntario> {
    public DentistaRepository() {
        super(DentistaVoluntario.class, "Dentista");
    }

    public List<DentistaVoluntario> listAvailable() {
        return em.createQuery("""
                select d from DentistaVoluntario d
                join fetch d.usuario u
                left join fetch d.especialidadePrincipal
                where u.status = :status and d.aceitaNovosPacientes = true
                """, DentistaVoluntario.class)
                .setParameter("status", StatusUsuario.ativo)
                .getResultList();
    }
}
