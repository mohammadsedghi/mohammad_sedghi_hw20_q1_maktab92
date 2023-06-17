package service.impl;

import base.service.BaseService;
import base.service.impl.BaseServiceImpl;
import entity.Book;
import entity.Librarian;
import repository.BookRepository;
import repository.LibrarianRepository;
import service.LibrarianService;

public class LibrarianServiceImpl extends BaseServiceImpl<Librarian,Long, LibrarianRepository>
        implements LibrarianService {
    private LibrarianRepository librarianRepository;
    public LibrarianServiceImpl(LibrarianRepository repository) {
        super(repository);
        this.librarianRepository=repository;
    }
}
