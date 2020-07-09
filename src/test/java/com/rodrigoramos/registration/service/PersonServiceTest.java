package com.rodrigoramos.registration.service;

import com.rodrigoramos.registration.model.Person;
import com.rodrigoramos.registration.repository.PersonRepository;
import com.rodrigoramos.registration.service.exception.ObjectNotFoundException;
import com.rodrigoramos.registration.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Test
    @DisplayName("Test findById Success")
    void findPersonByIdTest() {
        //given
        Person person = Person.builder()
                .id(1L)
                .cpf("70804614059")
                .email("adamastor@bol.com.br")
                .fullName("Adamastor Silva")
                .nationality("Brazilian")
                .placeOfBirth("Florianópolis")
                .gender("Masculino")
                .build();
        given(personRepository.findById(1L)).willReturn(Optional.of(person));

        //when
        Person savedPerson = personService.findById(1L);

        //then
        Assertions.assertNotNull(savedPerson);
        Assertions.assertEquals(person, savedPerson);
    }

    @Test
    @DisplayName("Test findById Not Found")
    void findPersonByIdNotFound() {
        Exception exception = assertThrows(
                ObjectNotFoundException.class,
                () -> personService.findById(22L));

        Assertions.assertTrue(exception.getMessage().contains("not found"));
    }


}
