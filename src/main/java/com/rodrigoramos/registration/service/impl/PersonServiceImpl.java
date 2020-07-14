package com.rodrigoramos.registration.service.impl;

import com.rodrigoramos.registration.dto.PersonUpdateDto;
import com.rodrigoramos.registration.model.Person;
import com.rodrigoramos.registration.repository.PersonRepository;
import com.rodrigoramos.registration.service.PersonService;
import com.rodrigoramos.registration.service.exception.ObjectNotFoundException;
import com.rodrigoramos.registration.utils.JsonNullableUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    @Transactional
    public Person save(Person entity) {
        return personRepository.save(entity);
    }

    @Override
    public Person findById(Long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.orElseThrow(() -> new ObjectNotFoundException(
                "Person not found! Id: " + id + ", Tipo: " + Person.class.getName()));
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        findById(id);
        personRepository.deleteById(id);
    }

    @Override
    public Person update(PersonUpdateDto personUpdateDto, Long id) {
        Person person = findById(id);

        JsonNullableUtils.changeIfPresent(personUpdateDto.getFullName(), person::setFullName);
        JsonNullableUtils.changeIfPresent(personUpdateDto.getEmail(), person::setEmail);
        JsonNullableUtils.changeIfPresent(personUpdateDto.getPlaceOfBirth(), person::setPlaceOfBirth);
        JsonNullableUtils.changeIfPresent(personUpdateDto.getNationality(), person::setNationality);

        return personRepository.save(person);
    }
}
