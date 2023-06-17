package service.impl;

import base.service.impl.BaseServiceImpl;
import entity.Librarian;
import entity.Member;
import repository.LibrarianRepository;
import repository.MemberRepository;
import service.LibrarianService;
import service.MemberService;

public class MemberServiceImpl extends BaseServiceImpl<Member,Long, MemberRepository>
        implements MemberService {
    private MemberRepository memberRepository;
    public MemberServiceImpl(MemberRepository repository) {
        super(repository);
        this.memberRepository=repository;
    }
}
