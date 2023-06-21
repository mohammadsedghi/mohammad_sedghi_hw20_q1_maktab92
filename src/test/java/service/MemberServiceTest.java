package service;

import entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import service.impl.MemberServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class MemberServiceTest {
    MemberService memberService;
    @InjectMocks
    Member member;

    @BeforeEach
    void setUp() {

        memberService = Mockito.mock(MemberServiceImpl.class);
        member=Mockito.mock(Member.class);
    }
    @Test
    void validator(){
        Mockito.when(memberService.validate(member)).thenReturn(true);
        Assertions.assertEquals(true,memberService.validate(member));
    }
    @Test
    void signUp(){
        Mockito.doNothing().when(memberService).signUp(member);
        verify(memberService).signUp(member);
    }

}