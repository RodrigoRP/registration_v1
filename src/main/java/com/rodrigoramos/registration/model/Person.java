package com.rodrigoramos.registration.model;

import com.rodrigoramos.registration.model.enums.GenderPerson;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private GenderPerson gender;
    private String email;
    private LocalDate dateOfBirth;
    private String placeOfBirth;
    private String nationality;
    private String cpf;

}
