package service;

import entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import service.impl.MemberServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    @InjectMocks
    Member member;

    @BeforeEach
    void setUp() {
        memberService = Mockito.mock(MemberServiceImpl.class);
    }
}