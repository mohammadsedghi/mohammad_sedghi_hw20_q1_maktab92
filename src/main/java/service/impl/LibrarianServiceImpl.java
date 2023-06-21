package service.impl;

import base.service.BaseService;
import base.service.impl.BaseServiceImpl;
import entity.Book;
import entity.Librarian;
import entity.Person;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import repository.BookRepository;
import repository.LibrarianRepository;
import service.LibrarianService;

import java.util.Set;

import static org.hibernate.query.sqm.tree.SqmNode.log;

public class LibrarianServiceImpl extends BaseServiceImpl<Librarian, Long, LibrarianRepository>
        implements LibrarianService {
    protected LibrarianRepository librarianRepository;

    public LibrarianServiceImpl(LibrarianRepository repository) {
        super(repository);
        this.librarianRepository = repository;
    }

    @Override
    public Boolean validate(Librarian librarian) {
        ValidatorFactory factory = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();

        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Person>> violations = validator.validate(librarian);
        if (violations.size() > 0) {
            for (ConstraintViolation<Person> violation : violations) {
                log.error(violation.getMessage());
            }
            factory.close();
            return false;
        } else {
            signUp(librarian);
            return true;
        }
    }

    @Override
    public void signUp(Librarian librarian) {
        librarianRepository.getSession().getTransaction().begin();
        librarianRepository.save(librarian);
        librarianRepository.getSession().getTransaction().commit();
    }
}
