package base.service.impl;

import base.BaseEntity;
import base.repository.BaseRepository;
import base.service.BaseService;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

public class BaseServiceImpl<E extends BaseEntity<ID>,ID extends Serializable,REPOSITORY extends BaseRepository<E,ID>>
        implements BaseService <E,ID>{
    private REPOSITORY repository;

    public BaseServiceImpl(REPOSITORY repository) {
        this.repository = repository;
    }


    @Override
    public E save(E entity) {
        repository.getSession().getTransaction().begin();
        repository.save(entity);
        repository.getSession().getTransaction().commit();
        return entity;
    }

    @Override
    public E update(E entity) {
        repository.getSession().getTransaction().begin();
        repository.update(entity);
        repository.getSession().getTransaction().commit();
        return entity;
    }

    @Override
    public Collection<E> loadAll() {
        return repository.loadAll();
    }

    @Override
    public Optional<E> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public Optional<E> findByEntity(E entity) {
        return repository.findByEntity(entity);
    }

    @Override
    public E remove(E entity) {
        repository.getSession().getTransaction().begin();
        repository.remove(entity);
        repository.getSession().getTransaction().commit();
        return entity;
    }


}
