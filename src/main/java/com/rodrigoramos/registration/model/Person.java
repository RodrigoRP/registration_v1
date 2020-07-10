package com.rodrigoramos.registration.model;

import com.rodrigoramos.registration.model.enums.GenderPerson;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "Name cannot be null")
    private String fullName;

    @Enumerated
    private GenderPerson gender;

    @Email(message = "Email should be valid")
    private String email;

    private LocalDate dateOfBirth;

    private String placeOfBirth;

    private String nationality;

    @CPF(message = "Invalid CPF")
    @Column(unique = true)
    private String cpf;

}
