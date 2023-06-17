package repository.impl;

import base.repository.BaseRepository;
import base.repository.impl.BaseRepositoryImpl;

import entity.Librarian;
import org.hibernate.Session;

public class LibrarianRepositoryImpl extends BaseRepositoryImpl<Librarian,Long>
        implements BaseRepository<Librarian,Long> {
    private Session session;
    public LibrarianRepositoryImpl(Session session) {
        super(session);
        this.session=session;
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    protected Class<Librarian> getEntityClass() {
        return Librarian.class;
    }
}
