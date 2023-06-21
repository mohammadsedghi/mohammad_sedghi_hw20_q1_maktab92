package repository.impl;

import base.repository.BaseRepository;
import base.repository.impl.BaseRepositoryImpl;

import entity.Subject;
import org.hibernate.Session;
import repository.SubjectRepository;

import java.util.Collection;
import java.util.Set;

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

    @Override
    public Collection<Subject> isExistBookForSubject() {
        String hql="select s from Subject s where exists (select b.subject from Book b where b.subject=s)";
        return session.createQuery(hql,Subject.class).getResultList();
    }
}
