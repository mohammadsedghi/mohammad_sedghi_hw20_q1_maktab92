package service;

import entity.Subject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import service.impl.SubjectServiceImpl;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class SubjectServiceTest {
SubjectService subjectService;
    Collection<Subject> subjects;
    @InjectMocks
    Subject subject;
    @BeforeEach
    void setUp() {
        subjectService= Mockito.mock(SubjectServiceImpl.class);
     subjects=Mockito.mock();
    }

    @Test
    void isExistBookForSubject() {
      //  Mockito.doNothing().when(subjectService.isExistBookForSubject());
        Mockito.when(subjectService.isExistBookForSubject()).thenReturn(subjects);
        Assertions.assertEquals(subjects,subjectService.isExistBookForSubject());
    }
}