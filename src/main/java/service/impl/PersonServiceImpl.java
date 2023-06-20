package service.impl;

import base.service.impl.BaseServiceImpl;
import entity.Book;
import entity.Librarian;
import entity.Member;
import entity.Person;

import repository.PersonRepository;

import service.PersonService;
import view.Menu;


import java.util.Optional;

public class PersonServiceImpl extends BaseServiceImpl<Person, Long, PersonRepository>
        implements PersonService {
    private PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository repository) {
        super(repository);
        this.personRepository = repository;
    }

//    @Override
//    public Person findByUsernameAndPassword(String username, String password) {
//
////        Optional<Person> optionalPerson = Optional.ofNullable(personRepository.findByUsernameAndPassword(username, password));
////        if (optionalPerson.isPresent()) {
////            return optionalPerson.get();
////        } else return null;
//
//        Optional<Person> optionalPerson =personRepository.findByUsernameAndPassword(username, password);
//
//        optionalPerson.ifPresentOrElse(
//                person -> {
//                    Menu.user = person;
//                },
//                () -> {
//                    // Handle the case when person is not found
//                    System.out.println("Person not found. Start login again.");
//
//                    // Start the login process again or take appropriate action
//                }
//        );
//        return null;
//    }


    @Override
    public Librarian findByUsernameAndPasswordForAdmin(String username, String password) {
        Optional<Librarian> optionalPerson =personRepository.findByUsernameAndPasswordForAdmin(username, password);

        optionalPerson.ifPresentOrElse(
                person -> {
                    Menu.userLibrarian = person;
                },
                () -> {
                    // Handle the case when person is not found
                    System.out.println("Person not found. Start login again.");
                    Menu menu=new Menu();
                    menu.logIn();
                    // Start the login process again or take appropriate action
                }
        );
        return null;
    }

    @Override
    public Member findByUsernameAndPasswordForMember(String username, String password) {
        Optional<Member> optionalPerson =personRepository.findByUsernameAndPasswordForMember(username, password);

        optionalPerson.ifPresentOrElse(
                member -> {
                    Menu.userMember = member;
                },
                () -> {
                    // Handle the case when person is not found
                    System.out.println("Person not found. Start login again.");
                    Menu menu=new Menu();
                    menu.logIn();
                    // Start the login process again or take appropriate action
                }
        );
        return null;
    }
}