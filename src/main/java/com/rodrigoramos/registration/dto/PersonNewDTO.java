package com.rodrigoramos.registration.dto;

import com.rodrigoramos.registration.model.enums.GenderPerson;
import com.rodrigoramos.registration.service.validation.PersonInsert;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@PersonInsert
public class PersonNewDTO implements Serializable {

    @NotEmpty(message = "Name cannot be null")
    private String fullName;

    @Enumerated
    private GenderPerson gender;

    @Email(message = "Email should be valid")
    private String email;

    private String dateOfBirth;

    private String placeOfBirth;

    private String nationality;

    @CPF(message = "Invalid CPF")
    @Column(unique = true)
    private String cpf;

}
