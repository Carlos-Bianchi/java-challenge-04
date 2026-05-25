package br.com.turmadobem.backend.repository;

import br.com.turmadobem.backend.exception.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

public abstract class BaseRepository<T> {
    @PersistenceContext
    protected EntityManager em;

    private final Class<T> type;
    private final String resourceName;

    protected BaseRepository(Class<T> type, String resourceName) {
        this.type = type;
        this.resourceName = resourceName;
    }

    public T findById(Long id) {
        T entity = em.find(type, id);
        if (entity == null) {
            throw new NotFoundException(resourceName, id);
        }
        return entity;
    }

    public List<T> listAll() {
        return em.createQuery("select e from " + type.getSimpleName() + " e", type).getResultList();
    }

    public T save(T entity) {
        em.persist(entity);
        return entity;
    }

    public T update(T entity) {
        return em.merge(entity);
    }

    public void delete(T entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }
}
