package service;

import base.service.BaseService;
import entity.Book;
import entity.Librarian;
import entity.Member;

public interface MemberService extends BaseService<Member,Long> {
    Boolean validate(Member member);

    void signUp(Member member );
}
