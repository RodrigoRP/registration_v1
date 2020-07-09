package com.rodrigoramos.registration.service.impl;

import com.rodrigoramos.registration.model.Person;
import com.rodrigoramos.registration.repository.PersonRepository;
import com.rodrigoramos.registration.service.PersonService;
import com.rodrigoramos.registration.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public Person save(Person entity) {
        return personRepository.save(entity);
    }

    @Override
    public Person findById(Long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.orElseThrow(() -> new ObjectNotFoundException(
                "Person not found! Id: " + id + ", Tipo: " + Person.class.getName()));
    }
}