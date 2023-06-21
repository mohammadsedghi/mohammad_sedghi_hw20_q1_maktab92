package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.util.Set;

/**
 * this class is so important because many to many relation defined
 * and more than another class have relation with another entity
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Member extends Person {
    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "book_id"), joinColumns=@JoinColumn(name = "member_id"),name = "bookReserve")
    Set<Book> bookReserveList;
    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "book_id"), joinColumns=@JoinColumn(name = "member_id"),name = "bookBorrowed")
    Set<Book> bookBorrowedList;
    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "book_id"), joinColumns=@JoinColumn(name = "member_id"),name = "bookRenewalDeadline")
    Set<Book> bookRenewalDeadlineList;
    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "book_id"), joinColumns=@JoinColumn(name = "member_id"),name = "historyOFBorrowedBook")
    Set<Book> historyOFBorrowedBookList;
    public Member(String name, String family, String nationalId, String username, String password, int age) {
        super(name, family, nationalId, username, password, age);

    }


}
