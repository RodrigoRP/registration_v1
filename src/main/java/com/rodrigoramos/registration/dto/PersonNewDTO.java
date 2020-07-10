package com.rodrigoramos.registration.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonNewDTO {

    private String fullName;
    private String gender;
    private String email;
    private LocalDate dateOfBirth;
    private String placeOfBirth;
    private String nationality;
    private String cpf;
}
