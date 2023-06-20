package view;


import base.repository.util.HibernateUtil;
import entity.*;
import org.hibernate.Session;
import repository.*;
import repository.impl.*;
import service.impl.*;

import java.util.*;

public class Menu {

    final Session session = HibernateUtil.getSessionFactory().openSession();
    LibrarianRepository librarianRepository = new LibrarianRepositoryImpl(session);
    LibrarianServiceImpl librarianService = new LibrarianServiceImpl(librarianRepository);
    MemberRepository memberRepository = new MemberRepositoryImpl(session);
    MemberServiceImpl memberService = new MemberServiceImpl(memberRepository);
    PersonRepository personRepository = new PersonRepositoryImpl(session);
    PersonServiceImpl personService = new PersonServiceImpl(personRepository);
    SubjectRepository subjectRepository = new SubjectRepositoryImpl(session);
    SubjectServiceImpl subjectService = new SubjectServiceImpl(subjectRepository);
    BookRepository bookRepository = new BookRepositoryImpl(session);
    BookServiceImpl bookService = new BookServiceImpl(bookRepository);

    Scanner scanner = new Scanner(System.in);
    public static Member userMember;
    public static Librarian userLibrarian;

    public void registration() {
        System.out.println("name");
        String name = scanner.next();
        System.out.println("family");
        String family = scanner.next();
        System.out.println("nationalId");
        String nationalId = scanner.next();
        System.out.println("username");
        String username = scanner.next();
        System.out.println("password");
        String password = scanner.next();
        System.out.println("age");
        int age = scanner.nextInt();
        System.out.println("degree");
        String degree = scanner.next();
        if (username.startsWith("1")) {
            Librarian librarian = new Librarian(name, family, nationalId, username, password, age, degree);
            librarianService.save(librarian);
        } else {
            Member member = new Member(name, family, nationalId, username, password, age);
            memberService.save(member);
        }
        logIn();
    }

    public void logIn() {
        System.out.println();
        System.out.println("===========******  Book reserve Library  ******===========");
        System.out.println();
        System.out.println("===========******  logIn");
        System.out.println("username:");
        String username = scanner.next();
        System.out.println("password:");
        String password = scanner.next();
        if (username.startsWith("1")) {
            personService.findByUsernameAndPasswordForAdmin(username, password);
            System.out.println("------------------------------------------------");
            System.out.println("Hello Admin your welcome . . . . ");
            System.out.println("librarian: " + userLibrarian.getName() + " " + userLibrarian.getFamily());
            runProgramForAdmin();
        } else {
            personService.findByUsernameAndPasswordForMember(username, password);
            System.out.println("member*");
            System.out.println(userMember.getName());
            runProgramForMember();
        }
    }

    public void runProgramForAdmin() {
        System.out.println();
        System.out.println("===========******  Main Menu  ******===========");
        System.out.println();
        System.out.println("1)assortment of subject          2)assortment of book");
        System.out.println();
        System.out.println();
        switch (scanner.nextInt()) {
            case 1:
                menuForSubject();
                break;
            case 2:
                menuForBook();
                break;
        }
    }


    public void menuForSubject() {
        System.out.println("===========******  assortment subject   ******================");
        System.out.println("""
                  1)Add subject
                  2)Edit subject
                  3)remove subject
                  4)load All  subject
                  5)load All  subject that their books exist
                  ************************************************************
                  6)assortment of book         7)back to main menu
                """);
        manageSubject();

    }

    public void manageSubject() {
        while (true) {
            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("===========================================================");
                    System.out.println("inter your subject");
                    String title = scanner.next();
                    Subject subject = new Subject(title);
                    subjectService.save(subject);
                    menuForSubject();
                    break;
                case 2:
                    System.out.println("===========================================================");
                    System.out.println("All Subject is: ");
                    Set<Subject> subjectSet = new HashSet<>(subjectService.loadAll());
                    for (Subject subject1 : subjectSet
                    ) {
                        System.out.println("this subject is : " + subject1);
                        System.out.println("do you want to update it? y->Yes  n->No");
                        if (scanner.next().equals("y")) {
                            System.out.println("inter new title");
                            String newTitle = scanner.next();
                            subject1.setTitle(newTitle);
                            subjectService.update(subject1);
                        }
                    }
                    menuForSubject();
                    break;
                case 3:
                    System.out.println("===========================================================");
                    System.out.println("All Subject is: ");
                    Set<Subject> subjectSetForRemove = new HashSet<>(subjectService.loadAll());
                    for (Subject subjectRemoveCandidate : subjectSetForRemove
                    ) {
                        System.out.println("this subject is : " + subjectRemoveCandidate);
                        System.out.println("do you want to remove it? y->Yes  n->No");
                        if (scanner.next().equals("y")) {
                            subjectService.remove(subjectRemoveCandidate);
                        }
                    }
                    menuForSubject();
                    break;
                case 4:
                    System.out.println("===========================================================");
                    System.out.println("All Subject is: ");
                    Set<Subject> subjectSetForShow = new HashSet<>(subjectService.loadAll());
                    for (Subject subjectRemoveCandidate : subjectSetForShow
                    ) {
                        System.out.println("******************************************************");
                        System.out.println("this subject is : " + subjectRemoveCandidate);
                    }
                    menuForSubject();
                    break;
                case 5:
                    menuForSubject();
                    break;
                case 6:
                    menuForBook();
                    break;
                case 7:
                    runProgramForAdmin();
                    break;
            }
        }
    }

    public void menuForBook() {
        System.out.println("===========******  assortment book   ******===========");
        System.out.println("1)Add book");
        System.out.println("2)load All  book with Edit subject and remove");
        System.out.println("************************************************************");
        System.out.println("3)assortment of subject          4)back to main menu");
        manageBook();
    }

    public void manageBook() {
        while (true) {
            switch (scanner.nextInt()) {
                case 1:
                    Set<Subject> subjectSet = new HashSet<>(subjectService.loadAll());
                    System.out.println("please select one subject from list ");
                    for (Subject subject : subjectSet) {
                        System.out.println("this is subject: " + subject);
                        System.out.println("do you want select it or no");
                        System.out.println("s -> select    n -> next subject");
                        if (scanner.next().equals("s")) {
                            System.out.println("inter author of this book");
                            String author = scanner.next();
                            System.out.println("inter your print year");
                            int printYear = scanner.nextInt();
                            Book book = new Book(subject, author, printYear);
                            bookService.save(book);
                            System.out.println("saved . . .");
                            break;
                        }

                    }
                    menuForBook();
                    break;
                case 2:
                    Set<Book> bookSetForEdit = new HashSet<>(bookService.loadAll());
                    if (bookSetForEdit.size()>0) {

                        boolean flag = true;
                        while (flag) {
                            for (Book book : bookSetForEdit) {

                                System.out.println("--------------------------------------------------------------------------");
                                System.out.println("subject: " + book.getSubject().getTitle() + "author: " + book.getAuthor() + "print year: " + book.getPrintYear());
                                System.out.println("--------------------------------------------------------------------------");
                                System.out.println("1)Edit subject                 2)remove                 3)next book");
                                switch (scanner.nextInt()) {
                                    case 1:
                                        Set<Subject> subjectSetForEdit = new HashSet<>(subjectService.loadAll());
                                        System.out.println("please select one subject from list ");
                                        for (Subject subject : subjectSetForEdit) {
                                            System.out.println("this is subject: " + subject.getTitle());
                                            System.out.println("do you want select it or no");
                                            System.out.println("s -> select    n -> next subject");
                                            if (scanner.next().equals("s")) {
                                                book.setSubject(subject);
                                                bookService.update(book);
                                                System.out.println("book subject is updated . . .");
                                                break;
                                            }
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Are you sure that remove this book? y ->yes  n ->no");
                                        if (scanner.next().equals("y")) {
                                            bookService.remove(book);
                                            System.out.println("removed . . .");
                                        }
                                        break;
                                    case 3:
                                        if (bookSetForEdit.size() == 1)
                                            System.out.println("number of book is one and not found another");
                                        System.out.println();
                                        break;
                                }

                                System.out.println("for going to menu of manage book press e");
                                if (scanner.next().equals("e")) {
                                    flag = false;
                                    break;
                                }
                            }
                        }
                    }else  System.out.println("number of book is zero and not found");
                    menuForBook();
                    break;
                case 3:
                    menuForSubject();
                    break;
                case 4:
                    runProgramForAdmin();
                    break;

            }
        }
    }

    public void runProgramForMember() {

    }


}
