package base.repository.impl;

import base.BaseEntity;
import base.repository.BaseRepository;
import org.hibernate.Session;
import org.yaml.snakeyaml.events.Event;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

public abstract class BaseRepositoryImpl<E extends BaseEntity<ID>, ID extends Serializable>
        implements BaseRepository<E, ID> {
    private final Session session;

    public BaseRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public E save(E entity) {
        session.persist(entity);
        return entity;
    }

    @Override
    public E update(E entity) {
        session.merge(entity);
        return entity;
    }

    @Override
    public Collection<E> loadAll() {
   return session.createQuery("from "+ getEntityClass().getSimpleName(),getEntityClass()).getResultList();
    }

    @Override
    public Optional<E> findById(ID id) {
        return Optional.ofNullable(session.find(getEntityClass(),id));
    }

    @Override
    public Optional<E> findByEntity(E entity) {
        return Optional.ofNullable(session.find(getEntityClass(),entity));
    }

    @Override
    public E remove(E entity) {
        session.remove(entity);
        return entity;
    }


    protected abstract Class<E> getEntityClass();

}
