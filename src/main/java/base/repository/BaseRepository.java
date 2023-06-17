package base.repository;

import base.BaseEntity;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

public interface BaseRepository <E extends BaseEntity<ID>,ID extends Serializable> {
    E save(E entity);
    E update(E entity);
    Collection<E> loadAll();
    Optional<E> findById(ID id);
    Optional<E> findByEntity(E entity);
    E remove(E entity);
    Session getSession();
}
