package service.impl;

import base.service.impl.BaseServiceImpl;

import entity.Librarian;
import entity.Member;
import entity.Person;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import repository.PersonRepository;

import service.PersonService;
import view.Menu;


import java.util.Optional;
import java.util.Set;

import static org.hibernate.query.sqm.tree.SqmNode.log;

public class PersonServiceImpl extends BaseServiceImpl<Person, Long, PersonRepository>
        implements PersonService {
    private PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository repository) {
        super(repository);
        this.personRepository = repository;
    }


    @Override
    public Librarian findByUsernameAndPasswordForAdmin(String username, String password) {
        Optional<Librarian> optionalPerson = personRepository.findByUsernameAndPasswordForAdmin(username, password);

        optionalPerson.ifPresentOrElse(
                person -> {
                    Menu.userLibrarian = person;
                },
                () -> {

                    System.out.println("Person not found. Start login again.");
                    Menu menu = new Menu();
                    menu.logIn();

                }
        );
        return null;
    }

    @Override
    public Member findByUsernameAndPasswordForMember(String username, String password) {
        Optional<Member> optionalPerson = personRepository.findByUsernameAndPasswordForMember(username, password);

        optionalPerson.ifPresentOrElse(
                member -> {
                    Menu.userMember = member;
                },
                () -> {
                    System.out.println("Person not found. Start login again.");
                    Menu menu = new Menu();
                    menu.logIn();

                }
        );
        return null;
    }


}