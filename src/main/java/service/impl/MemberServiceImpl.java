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
import repository.LibrarianRepository;
import repository.MemberRepository;
import service.LibrarianService;
import service.MemberService;

import java.util.Set;

import static org.hibernate.query.sqm.tree.SqmNode.log;

public class MemberServiceImpl extends BaseServiceImpl<Member, Long, MemberRepository>
        implements MemberService {
    private MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository repository) {
        super(repository);
        this.memberRepository = repository;
    }

    @Override
    public Boolean validate(Member member) {
        ValidatorFactory factory = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();

        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Person>> violations = validator.validate(member);
        if (violations.size() > 0) {
            for (ConstraintViolation<Person> violation : violations) {
                log.error(violation.getMessage());
            }
            factory.close();
            return false;
        } else {
            signUp(member);
            return true;
        }
    }

    @Override
    public void signUp(Member member) {
        memberRepository.getSession().getTransaction().begin();
        memberRepository.save(member);
        memberRepository.getSession().getTransaction().commit();
    }
}
