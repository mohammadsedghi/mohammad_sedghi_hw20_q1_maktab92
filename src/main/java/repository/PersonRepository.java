package repository;

import base.repository.BaseRepository;
import entity.Librarian;
import entity.Member;
import entity.Person;
import entity.Subject;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends BaseRepository<Person,Long> {
  Optional<Librarian> findByUsernameAndPasswordForAdmin(String username, String password);
  Optional<Member> findByUsernameAndPasswordForMember(String username, String password);

}
