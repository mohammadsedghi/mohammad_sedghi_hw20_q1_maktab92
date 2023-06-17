package service.impl;

import base.repository.BaseRepository;
import base.repository.impl.BaseRepositoryImpl;
import base.service.BaseService;
import base.service.impl.BaseServiceImpl;
import entity.Book;
import repository.BookRepository;
import service.BookService;

public class BookServiceImpl  extends BaseServiceImpl<Book,Long, BookRepository>
        implements BookService {
    private BookRepository bookRepository;
    public BookServiceImpl(BookRepository repository) {
        super(repository);
        this.bookRepository=repository;
    }
}
