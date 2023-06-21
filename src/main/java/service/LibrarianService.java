package service;

import base.service.BaseService;
import entity.Book;
import entity.Librarian;
import entity.Person;

public interface LibrarianService extends BaseService<Librarian, Long> {
    Boolean validate(Librarian librarian);

    void signUp(Librarian librarian);
}
