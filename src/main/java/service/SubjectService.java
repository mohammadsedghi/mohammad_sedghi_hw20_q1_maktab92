package service;

import base.service.BaseService;
import entity.Book;
import entity.Subject;

import java.util.Collection;
import java.util.Set;

public interface SubjectService extends BaseService<Subject,Long> {
   Collection<Subject> isExistBookForSubject();
}
