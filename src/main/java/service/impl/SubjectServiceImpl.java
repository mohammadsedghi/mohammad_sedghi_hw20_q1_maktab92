package service.impl;

import base.service.impl.BaseServiceImpl;
import entity.Librarian;
import entity.Subject;
import repository.LibrarianRepository;
import repository.SubjectRepository;
import service.LibrarianService;
import service.SubjectService;

import java.util.Collection;
import java.util.Set;

public class SubjectServiceImpl extends BaseServiceImpl<Subject,Long, SubjectRepository>
        implements SubjectService {
    private SubjectRepository subjectRepository;
    public SubjectServiceImpl(SubjectRepository repository) {
        super(repository);
        this.subjectRepository=repository;
    }

    @Override
    public Collection<Subject> isExistBookForSubject() {
        return subjectRepository.isExistBookForSubject();
    }
}
