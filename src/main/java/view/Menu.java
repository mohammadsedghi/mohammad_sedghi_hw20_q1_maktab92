package view;


import base.repository.util.HibernateUtil;
import entity.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.hibernate.Session;
import repository.*;
import repository.impl.*;
import service.impl.*;

import java.time.LocalDate;
import java.util.*;

/**
 * this program design for library that admin manage something for example manage books,members,subject
 * and then confirm of renewal borrowed book ,etc .
 * this class write for kind of menu for both admin and members
 * program after running in main method goes to this class .
 * this program written with intellij idea and jdk 16.0.2
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
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
            Set<Book> temporarySet=new HashSet<>();
            member.setBookRenewalDeadlineList(temporarySet);
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
            System.out.println("HELLO YOUR WELCOME");
            System.out.println("name: " + userMember.getName() + "family: " + userMember.getFamily());
            runProgramForMember();
        }
    }

    public void runProgramForAdmin() {
        System.out.println();
        System.out.println("===========******  Main Menu  ******===========");
        System.out.println();
        System.out.println("1)assortment of subject          2)assortment of book          3)logOut");
        System.out.println();
        System.out.println();
        switch (scanner.nextInt()) {
            case 1:
                menuForSubject();
                break;
            case 2:
                menuForBook();
                break;
            case 3:logIn();
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
                  6)assortment of book 
                  7)back to main menu
                  -------------------------------------------------------------------------
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
                    Set<Subject> subjects=new HashSet<>(subjectService.isExistBookForSubject());
                    System.out.println("subject that exist at least one book for it");
                    for (Subject subjectCandidate : subjects
                    ){

                        System.out.println("title: "+subjectCandidate.getTitle()+subjectCandidate);
                }
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
        System.out.println("3)confirm reserved book or confirm renewal deadline");
        System.out.println("4)assortment of subject");
        System.out.println("5)back to main menu");
        System.out.println("--------------------------------------------------------");

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
                            System.out.println("number of stock");
                            int numberOfStuck = scanner.nextInt();
                            Book book = new Book(subject, author, printYear, numberOfStuck);
                            bookService.save(book);
                            System.out.println("saved . . .");
                            break;
                        }

                    }
                    menuForBook();
                    break;
                case 2:
                    Set<Book> bookSetForEdit = new HashSet<>(bookService.loadAll());
                    if (bookSetForEdit.size() > 0) {

                        boolean flag = true;
                        while (flag) {
                            for (Book book : bookSetForEdit) {

                                System.out.println("--------------------------------------------------------------------------");
                                System.out.println("subject: " + book.getSubject().getTitle() + "author: " + book.getAuthor() + "print year: " + book.getPrintYear()+" stock: "+book.getNumberOfStock());
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
                    } else System.out.println("number of book is zero and not found");
                    menuForBook();
                    break;
                case 3:

                    Set<Member> members = new HashSet<>(memberService.loadAll());
                    Set<Book> temporaryHistoryBorrowedList;
                    for (Member member : members
                    ) {
                        System.out.println("name: " + member.getName() + " family: " + member.getFamily());
                        if (member.getBookReserveList().size() > 0) {
                            System.out.println("======================*****  reserve part  ******======================");
                            Set<Book> temporaryReserveBook = new HashSet<>(member.getBookReserveList());
                            Set<Book> temporaryBorrowedBook;
                            if (member.getBookBorrowedList() == null) {
                                temporaryBorrowedBook = new HashSet<>();
                            } else {
                                temporaryBorrowedBook = new HashSet<>(member.getBookBorrowedList());
                            }
                            for (Book book : temporaryReserveBook
                            ) {
                                System.out.println("book ==> subject: " + book.getSubject().getTitle() + "  author: " + book.getAuthor() + "  print year: " + book.getPrintYear()+" stock: "+book.getNumberOfStock());
                                System.out.println("do you want to confirm reserve it? y ->yes  n -> no");
                                if (scanner.next().equals("y")) {
                                    book.setNumberOfStock(book.getNumberOfStock() - 1);
                                    bookService.update(book);
                                    temporaryReserveBook.remove(book);
                                    temporaryBorrowedBook.add(book);
                                    if (member.getHistoryOFBorrowedBookList() == null) {
                                        temporaryHistoryBorrowedList = new HashSet<>();
                                    } else {
                                        temporaryHistoryBorrowedList = new HashSet<>(member.getHistoryOFBorrowedBookList());
                                    }
                                    temporaryHistoryBorrowedList.add(book);
                                    member.setHistoryOFBorrowedBookList(temporaryHistoryBorrowedList);
                                    member.setBookReserveList(temporaryReserveBook);
                                    member.setBookBorrowedList(temporaryBorrowedBook);
                                    memberService.update(member);
                                    System.out.println("updated . . .");
                                }
                            }
                        }

                        if (member.getBookRenewalDeadlineList().size() > 0) {
                            System.out.println("======================*****  renewal part  ******======================");
                            Set<Book> temporaryRenewalDeadlineList = new HashSet<>(member.getBookRenewalDeadlineList());
                            Set<Book> copyTemporaryRenewalDeadlineList = new HashSet<>(member.getBookRenewalDeadlineList());
                            Set<Book> temporaryBorrowedList = new HashSet<>(member.getBookBorrowedList());
                            Set<Book> copyTemporaryBorrowedList = new HashSet<>(member.getBookBorrowedList());

                            for (Book book : temporaryRenewalDeadlineList
                            ) {
                                System.out.println();
                                System.out.println("book ==> subject: " + book.getSubject().getTitle() + "  author: " + book.getAuthor() + "  print year: " + book.getPrintYear()+" stock: "+book.getNumberOfStock());
                                System.out.println();
                                System.out.println("do you want to confirm renewal it? 1->yes  2 -> no");
                                if (scanner.nextInt()==1) {
                                   copyTemporaryRenewalDeadlineList.remove(book);
                                    copyTemporaryBorrowedList.remove(book);
                                    book.setRenewalDeadline(LocalDate.of(book.getRenewalDeadline().getYear(), book.getRenewalDeadline().getMonthValue() + 1, book.getRenewalDeadline().getDayOfMonth()));
                                    copyTemporaryBorrowedList.add(book);
                                    member.setBookRenewalDeadlineList(copyTemporaryRenewalDeadlineList);
                                    member.setBookBorrowedList(copyTemporaryBorrowedList);
                                    bookService.update(book);
                                    memberService.update(member);
                                    System.out.println("deadline for one month is renewal until: "+book.getRenewalDeadline());
                                } else {
                                    copyTemporaryRenewalDeadlineList.remove(book);
                                    copyTemporaryBorrowedList.remove(book);
                                    if (member.getHistoryOFBorrowedBookList() == null) {
                                        temporaryHistoryBorrowedList = new HashSet<>();
                                    } else {
                                        temporaryHistoryBorrowedList = new HashSet<>(member.getHistoryOFBorrowedBookList());
                                    }
                                    temporaryHistoryBorrowedList.add(book);
                                    member.setHistoryOFBorrowedBookList(temporaryHistoryBorrowedList);
                                    member.setBookRenewalDeadlineList(copyTemporaryRenewalDeadlineList);
                                    member.setBookRenewalDeadlineList(copyTemporaryBorrowedList);
                                    book.setNumberOfStock(book.getNumberOfStock() + 1);
                                    bookService.update(book);
                                    memberService.update(member);
                                    System.out.println("deadline is the end and book is free to reserved");
                                }
                            }
                        }
                    }
                    menuForBook();
                    break;
                case 4:
                    menuForSubject();
                    break;
                case 5:
                    runProgramForAdmin();
                    break;

            }
        }
    }

    public void runProgramForMember() {
        while (true) {
            System.out.println("""
                    ===========******  Home   ******==========="
                    1)profile
                    2)Borrowing books
                    3)Extending the book loan
                    4)history of Borrowed books
                    ----------------------------
                    5)logout
                    """);
            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("name: " + userMember.getName());
                    System.out.println("family: " + userMember.getFamily());
                    System.out.println("age: " + userMember.getAge());
                    System.out.println("nationalID: " + userMember.getNationalId());
                    break;
                case 2:
                    Set<Book> bookSet = new HashSet<>(bookService.loadAll());
                    for (Book book : bookSet) {
                        System.out.println("subject: " + book.getSubject().getTitle() + "  author: " + book.getAuthor() + "  print year: " + book.getPrintYear()+" stock: "+book.getNumberOfStock());
                        System.out.println("do you want to borrow it? y ->yes   n -> no");
                        if (scanner.next().equals("y")) {
                            if (book.getNumberOfStock() > 0) {
                                System.out.println("please wait for admin confirm your borrowing.");
                                Set<Book> bookReserveSet = new HashSet<>();
                                book.setRenewalDeadline(LocalDate.now());
                                bookReserveSet.add(book);
                                userMember.setBookReserveList(bookReserveSet);
                                memberService.update(userMember);
                                System.out.println("this book is reserved . . . ");
                            } else System.out.println("this book is not exist");
                        }
                    }
                    break;
                case 3:
                    if (userMember.getBookBorrowedList().size() > 0) {
                        Set<Book> bookSetBorrowed = new HashSet<>(userMember.getBookBorrowedList());
                        for (Book book : bookSetBorrowed) {
                            System.out.println("subject: " + book.getSubject().getTitle() + "author: " + book.getAuthor() + "print year: " + book.getPrintYear()+" stock: "+book.getNumberOfStock());
                            System.out.println(book.getRenewalDeadline());
                            System.out.println("do you want to renewal deadline? y ->yes  n -> no");
                            if (scanner.next().equals("y")) {
                                Set<Book> bookSetRenewal;
                                if (userMember.getBookRenewalDeadlineList()==null){
                                    bookSetRenewal = new HashSet<>();
                                }else {
                                bookSetRenewal = new HashSet<>(userMember.getBookRenewalDeadlineList());
                                }
                                book.setRenewalDeadline(LocalDate.of(book.getRenewalDeadline().getYear(),book.getRenewalDeadline().getMonthValue(),book.getRenewalDeadline().getDayOfMonth()));
                               bookService.update(book);
                                bookSetRenewal.add(book);
                                userMember.setBookRenewalDeadlineList(bookSetRenewal);
                                memberService.update(userMember);
                                System.out.println("book renewal deadline is send");
                            }
                            System.out.println("------------------------------------------------------------");
                        }
                    } else System.out.println("you have not any book borrowed until now");
                    break;
                case 4:
                    if (userMember.getHistoryOFBorrowedBookList().size() > 0) {
                        for (Book book : userMember.getHistoryOFBorrowedBookList()
                        ) {
                            System.out.println("subject: " + book.getSubject().getTitle() + "author: " + book.getAuthor() + "print year: " + book.getPrintYear()+" stock: "+book.getNumberOfStock());
                            System.out.println("------------------------------------------------------------------");
                        }

                    } else System.out.println("you have not borrowed any book until now");
                    break;
                case 5:
                    logIn();
                    break;
            }
        }
    }


}
