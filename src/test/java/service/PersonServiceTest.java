package service;

import entity.Librarian;
import entity.Member;
import entity.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import service.impl.PersonServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {
PersonService personService;
Optional<Person> person;
String username;
String password;
@InjectMocks
Librarian librarian;
@InjectMocks
    Member member;
    @BeforeEach
    void setUp() {
        personService= Mockito.mock(PersonServiceImpl.class);
        person=Mockito.mock();
        username="ali";
        password="1234";
        librarian=Mockito.mock(Librarian.class);
        member=Mockito.mock(Member.class);
    }

    @Test
    void findByUsernameAndPasswordForAdmin() {
        Mockito.when(personService.findByUsernameAndPasswordForAdmin(username,password)).thenReturn(librarian);
        Assertions.assertEquals(librarian,personService.findByUsernameAndPasswordForAdmin(username,password));
    }

    @Test
    void findByUsernameAndPasswordForMember() {
        Mockito.when(personService.findByUsernameAndPasswordForMember(username,password)).thenReturn(member);
        Assertions.assertEquals(member,personService.findByUsernameAndPasswordForMember(username,password));
    }
}