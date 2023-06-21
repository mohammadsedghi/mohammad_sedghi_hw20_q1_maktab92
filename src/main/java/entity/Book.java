package entity;

import base.BaseEntity;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

/**
 * this class is defined for generated book  and
 * relation with another entity
 * all things that have relation with book is
 * defined in this class
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book extends BaseEntity<Long> {
    @ManyToOne
    Subject subject;
    String author;
    int printYear;
    int numberOfStock;
    LocalDate renewalDeadline;

    public Book(Subject subject, String author, int printYear, int numberOfStock) {
        this.subject = subject;
        this.author = author;
        this.printYear = printYear;
        this.numberOfStock = numberOfStock;
    }


}
