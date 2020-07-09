package com.rodrigoramos.registration.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonNewDTO {

    private String fullName;
    private String gender;
    private String email;
    private LocalDate dateOfBirth;
    private String placeOfBirth;
    private String nationality;
    private String cpf;
}
