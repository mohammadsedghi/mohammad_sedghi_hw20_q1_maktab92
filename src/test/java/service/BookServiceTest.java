package service;

import entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import service.impl.BookServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {
    BookService bookService;
    @InjectMocks
    Book book;

    @BeforeEach
    void setUp() {
        bookService = Mockito.mock(BookServiceImpl.class);
    }
}