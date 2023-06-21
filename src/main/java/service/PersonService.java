package service;

import base.service.BaseService;
import entity.Book;
import entity.Librarian;
import entity.Member;
import entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService extends BaseService<Person,Long> {
    Librarian findByUsernameAndPasswordForAdmin(String username, String password);
    Member findByUsernameAndPasswordForMember(String username, String password);

}
