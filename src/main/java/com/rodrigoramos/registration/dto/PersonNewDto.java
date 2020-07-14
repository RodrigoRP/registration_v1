package com.rodrigoramos.registration.dto;

import com.rodrigoramos.registration.model.enums.GenderPerson;
import com.rodrigoramos.registration.service.validation.PersonInsert;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@PersonInsert
public class PersonNewDto implements Serializable {

    @NotNull(message = "Name cannot be null")
    private String fullName;

    @Enumerated
    private GenderPerson gender;

    @Email(message = "Email should be valid")
    private String email;

    private LocalDate dateOfBirth;

    private String placeOfBirth;

    private String nationality;

    @CPF(message = "Invalid CPF")
    private String cpf;

}
