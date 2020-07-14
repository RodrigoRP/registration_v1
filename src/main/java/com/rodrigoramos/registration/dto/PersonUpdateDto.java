package com.rodrigoramos.registration.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonUpdateDto {

    @NotNull
    private JsonNullable<String> fullName = JsonNullable.undefined();

    @NotNull
    private JsonNullable<String> email = JsonNullable.undefined();

    @NotNull
    private JsonNullable<String> placeOfBirth = JsonNullable.undefined();

    @NotNull
    private JsonNullable<String> nationality = JsonNullable.undefined();



}
