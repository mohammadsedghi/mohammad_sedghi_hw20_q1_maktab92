package entity;

import base.BaseEntity;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@ToString
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Person extends BaseEntity<Long> {
    String name;
    String family;
    String nationalId;
    String username;
    String password;
    int age;

}
