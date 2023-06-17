package repository.impl;

import base.repository.BaseRepository;
import base.repository.impl.BaseRepositoryImpl;
import entity.Book;
import org.hibernate.Session;
import repository.BookRepository;

public class BookRepositoryImpl extends BaseRepositoryImpl<Book,Long>
        implements BookRepository {
    private Session session;

    public BookRepositoryImpl(Session session) {
        super(session);
        this.session=session;
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    protected Class<Book> getEntityClass() {
        return Book.class;
    }
}
