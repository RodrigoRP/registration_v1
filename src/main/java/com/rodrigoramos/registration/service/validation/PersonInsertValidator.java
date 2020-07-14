package com.rodrigoramos.registration.service.validation;

import com.rodrigoramos.registration.controller.exception.FieldMessage;
import com.rodrigoramos.registration.dto.PersonNewDto;
import com.rodrigoramos.registration.model.Person;
import com.rodrigoramos.registration.repository.PersonRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PersonInsertValidator implements ConstraintValidator<PersonInsert, PersonNewDto> {

    private final PersonRepository repository;

    @Override
    public void initialize(PersonInsert constraintAnnotation) {

    }

    @Override
    public boolean isValid(PersonNewDto personNewDTO, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        Optional<Person> personByEmail = repository.findByEmail(personNewDTO.getEmail());
        Optional<Person> personByCpf = repository.findByCpf(personNewDTO.getCpf());

        if (personByEmail.isPresent()) {
            list.add(new FieldMessage("email", "Email already exists'"));
        }
        if (personByCpf.isPresent()) {
            list.add(new FieldMessage("cpf", "CPF already exists'"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();

    }
}
