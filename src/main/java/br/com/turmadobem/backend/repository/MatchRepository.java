package br.com.turmadobem.backend.repository;

import br.com.turmadobem.backend.model.Match;
import br.com.turmadobem.backend.model.StatusMatch;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MatchRepository extends BaseRepository<Match> {
    public MatchRepository() {
        super(Match.class, "Match");
    }

    public long countByStatus(StatusMatch status) {
        return em.createQuery("select count(m) from Match m where m.status = :status", Long.class)
                .setParameter("status", status)
                .getSingleResult();
    }
}
