package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Member extends Person {
    @ManyToMany
    List<Book> bookReserveList;
    public Member(String name, String family, String nationalId, String username, String password, int age) {
        super(name, family, nationalId, username, password, age);

    }


}
