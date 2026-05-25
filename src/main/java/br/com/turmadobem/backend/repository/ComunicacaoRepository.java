package br.com.turmadobem.backend.repository;

import br.com.turmadobem.backend.model.Comunicacao;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ComunicacaoRepository extends BaseRepository<Comunicacao> {
    public ComunicacaoRepository() {
        super(Comunicacao.class, "Comunicação");
    }

    public long countUnread() {
        return em.createQuery("select count(c) from Comunicacao c where c.lida = false", Long.class).getSingleResult();
    }
}
