package com.rodrigoramos.registration.service;

import com.rodrigoramos.registration.model.Person;
import com.rodrigoramos.registration.repository.PersonRepository;
import com.rodrigoramos.registration.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @InjectMocks
    PersonServiceImpl personService;

    @Mock
    PersonRepository personRepository;

    @Test
    @DisplayName("Test save Success")
    void savePersonTest() {
        //given
        Person person = new Person();
        given(personRepository.save(any(Person.class))).willReturn(person);

        //when
        Person savedPerson = personService.save(person);

        //then
        Assertions.assertNotNull(savedPerson);
        then(personRepository).should().save(any(Person.class));
    }


}
