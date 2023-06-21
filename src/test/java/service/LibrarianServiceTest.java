package service;

import entity.Librarian;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import service.impl.LibrarianServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

class LibrarianServiceTest {
    LibrarianService librarianService;
    @InjectMocks
    Librarian librarian;

    @BeforeEach
    void setUp() {
        librarianService = Mockito.mock(LibrarianServiceImpl.class);
        librarian=Mockito.mock(Librarian.class);
    }
    @Test
    void validator(){
        Mockito.when(librarianService.validate(librarian)).thenReturn(true);
        Assertions.assertEquals(true,librarianService.validate(librarian));
    }
    @Test
    void signUp(){
        Mockito.doNothing().when(librarianService).signUp(librarian);
        verify(librarianService).signUp(librarian);
    }
}