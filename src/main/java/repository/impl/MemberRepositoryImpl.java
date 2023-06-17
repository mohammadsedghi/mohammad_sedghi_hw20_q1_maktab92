package repository.impl;

import base.repository.BaseRepository;
import base.repository.impl.BaseRepositoryImpl;

import entity.Member;
import org.hibernate.Session;

public class MemberRepositoryImpl  extends BaseRepositoryImpl<Member,Long>
        implements BaseRepository<Member,Long> {
    private Session session;
    public MemberRepositoryImpl(Session session) {
        super(session);
        this .session=session;

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    protected Class<Member> getEntityClass() {
        return Member.class;
    }
}
