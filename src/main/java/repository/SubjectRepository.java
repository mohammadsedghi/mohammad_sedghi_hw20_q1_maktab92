package repository;

import base.repository.BaseRepository;
import entity.Book;
import entity.Subject;

import java.lang.reflect.Member;
import java.util.Collection;
import java.util.Set;

public interface SubjectRepository extends BaseRepository<Subject,Long> {
  Collection<Subject> isExistBookForSubject();
}
