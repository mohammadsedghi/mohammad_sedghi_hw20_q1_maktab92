package service;

import entity.Librarian;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import service.impl.LibrarianServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class LibrarianServiceTest {
    LibrarianService librarianService;
    @InjectMocks
    Librarian librarian;

    @BeforeEach
    void setUp() {
        librarianService = Mockito.mock(LibrarianServiceImpl.class);

    }
}