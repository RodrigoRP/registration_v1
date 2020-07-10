package com.rodrigoramos.registration.dto;

import com.rodrigoramos.registration.model.enums.GenderPerson;
import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonNewDTO implements Serializable {

    private String fullName;
    private GenderPerson gender;
    private String email;
    private String dateOfBirth;
    private String placeOfBirth;
    private String nationality;
    private String cpf;

}
