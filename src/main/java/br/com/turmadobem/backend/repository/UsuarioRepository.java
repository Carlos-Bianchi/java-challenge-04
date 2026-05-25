package br.com.turmadobem.backend.repository;

import br.com.turmadobem.backend.model.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class UsuarioRepository extends BaseRepository<Usuario> {
    public UsuarioRepository() {
        super(Usuario.class, "Usuário");
    }

    public Optional<Usuario> findByEmail(String email) {
        return em.createQuery("select u from Usuario u where lower(u.email) = lower(:email)", Usuario.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst();
    }

    public boolean existsByEmail(String email) {
        return !em.createQuery("select u.id from Usuario u where lower(u.email) = lower(:email)", Long.class)
                .setParameter("email", email)
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    public boolean existsByCpf(String cpf) {
        return !em.createQuery("select u.id from Usuario u where u.cpf = :cpf", Long.class)
                .setParameter("cpf", cpf)
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }
}
