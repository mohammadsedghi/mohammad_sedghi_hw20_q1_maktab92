package repository.impl;

import base.repository.BaseRepository;
import base.repository.impl.BaseRepositoryImpl;

import entity.Subject;
import org.hibernate.Session;
import repository.SubjectRepository;

public class SubjectRepositoryImpl  extends BaseRepositoryImpl<Subject,Long>
        implements SubjectRepository {
private Session session;

    public SubjectRepositoryImpl(Session session) {
        super(session);
        this.session=session;
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    protected Class<Subject> getEntityClass() {
        return Subject.class;
    }
}
