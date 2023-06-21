package entity;

import base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;


/**
 * about validator that use in this class
 * @ email for checked that username is true email or no
 * @ length for checked that national id is more than 10 character or no
 * positive and min that checked age is greater than zero and at least
 * person have ten years old other wise goes to another system library
 * that appropriate for child
 */
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
   // @Length(min = 10)
    String nationalId;
   // @Email
    String username;
    String password;
    @Positive
    @Min(value = 10)
    int age;

}
