package entity;


import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Librarian extends Person {
    String degree;
    public Librarian(String name, String family, String nationalId, String username, String password, int age,String degree) {
        super(name, family, nationalId, username, password, age);
        this.degree=degree;

    }
}
