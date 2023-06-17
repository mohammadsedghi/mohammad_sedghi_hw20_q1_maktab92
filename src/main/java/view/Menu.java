package view;


import base.repository.util.HibernateUtil;
import entity.Librarian;
import entity.Member;
import entity.Person;
import org.hibernate.Session;
import repository.LibrarianRepository;
import repository.MemberRepository;
import repository.impl.LibrarianRepositoryImpl;
import repository.impl.MemberRepositoryImpl;
import service.impl.LibrarianServiceImpl;
import service.impl.MemberServiceImpl;

import java.util.Scanner;

public class Menu {

    final Session session = HibernateUtil.getSessionFactory().openSession();
    LibrarianRepository librarianRepository = new LibrarianRepositoryImpl(session);
    LibrarianServiceImpl librarianService = new LibrarianServiceImpl(librarianRepository);
    MemberRepository memberRepository = new MemberRepositoryImpl(session);
    MemberServiceImpl memberService = new MemberServiceImpl(memberRepository);
    Scanner scanner = new Scanner(System.in);
    static Person user;
    public void registration(){
        System.out.println("name");
       String name= scanner.next();
        System.out.println("family");
        String family=scanner.next();
        System.out.println("nationalId");
        String nationalId=scanner.next();
        System.out.println("username");
        String username=scanner.next();
        System.out.println("password");
        String password=scanner.next();
        System.out.println("age");
        int age=scanner.nextInt();
        if (username.startsWith("1")){
            Librarian librarian = new Librarian(name,family,nationalId,username,password,age);
            librarianService.save(librarian);
        }else {
            Member member = new Member(name,family,nationalId,username,password,age);
            memberService.save(member);
        }
        logIn();
    }

    public void logIn() {
        System.out.println("username:--------");
        String username = scanner.next();
        System.out.println("password:--------");
        String password = scanner.next();
        if (username.startsWith("1")) {
            Librarian librarian = new Librarian();
            librarian.setUsername(username);
            librarian.setPassword(password);
            librarianService.findByEntity(librarian).ifPresent(librarian1 -> {
                new Librarian();
                user = librarian1;
            });
            System.out.println("Admin "+ user+"  your welcome");
        } else {
            Member member = new Member();
            member.setUsername(username);
            member.setPassword(password);
            memberService.findByEntity(member).ifPresent(member1 -> {
                        new Member();
                        user = member1;
                    }
            );
            System.out.println("member "+user+"  your welcome");
        }
    }

    public void runProgram() {
        System.out.println("===========******  Book reserve Library  ******===========");
    }
}
