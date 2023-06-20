package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.Librarian;
import entity.Member;
import entity.Person;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.PersonRepository;

import java.util.List;
import java.util.Optional;


public class PersonRepositoryImpl  extends BaseRepositoryImpl<Person,Long>
        implements PersonRepository {

  private Session session;

    public PersonRepositoryImpl(Session session) {
        super(session);
        this.session=session;
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    protected Class<Person> getEntityClass() {
        return Person.class;
    }

//    @Override
//    public Optional<Person> findByUsernameAndPassword(String username, String password) {
//       try {
//           String hql = "select p from Person p where p.username=:username AND p.password=:password";
//           Query<Person> user = session.createQuery(hql, Person.class);
//           user.setParameter("username", username);
//           user.setParameter("password", password);
//           return Optional.of(user.getSingleResult());
//       }catch (NoResultException e){
//          return Optional.empty();
//       }
//    }

    @Override
    public Optional<Librarian> findByUsernameAndPasswordForAdmin(String username, String password) {
        try {
            String hql = "select p from Librarian p where p.username=:username AND p.password=:password";
            Query<Librarian> user = session.createQuery(hql, Librarian.class);
            user.setParameter("username", username);
            user.setParameter("password", password);
            return Optional.of(user.getSingleResult());
        }catch (NoResultException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Member> findByUsernameAndPasswordForMember(String username, String password) {
        try {
            String hql = "select p from Member p where p.username=:username AND p.password=:password";
            Query<Member> user = session.createQuery(hql, Member.class);
            user.setParameter("username", username);
            user.setParameter("password", password);
            return Optional.of(user.getSingleResult());
        }catch (NoResultException e){
            return Optional.empty();
        }
    }
}

