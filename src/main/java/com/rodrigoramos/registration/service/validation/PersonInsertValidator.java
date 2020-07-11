package com.rodrigoramos.registration.service.validation;

import com.rodrigoramos.registration.controller.exception.FieldMessage;
import com.rodrigoramos.registration.dto.PersonNewDTO;
import com.rodrigoramos.registration.model.Person;
import com.rodrigoramos.registration.repository.PersonRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class PersonInsertValidator implements ConstraintValidator<PersonInsert, PersonNewDTO> {

    private final PersonRepository repository;

    @Override
    public void initialize(PersonInsert constraintAnnotation) {

    }

    @Override
    public boolean isValid(PersonNewDTO personNewDTO, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        Person email = repository.findByEmail(personNewDTO.getEmail());
        if (email != null) {
            list.add(new FieldMessage("email", "Email already exists'"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();

    }
}
