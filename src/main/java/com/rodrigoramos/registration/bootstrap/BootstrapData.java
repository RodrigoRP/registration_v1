package com.rodrigoramos.registration.bootstrap;

import com.rodrigoramos.registration.model.Person;
import com.rodrigoramos.registration.model.enums.GenderPerson;
import com.rodrigoramos.registration.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Profile("test")
@RequiredArgsConstructor
@Component
public class BootstrapData implements CommandLineRunner {

    private final PersonRepository personRepository;

    @Override
    public void run(String... args) throws Exception {
        Person person1 = Person.builder()
                .id(1L)
                .cpf("67456302065")
                .email("ronaldo@bol.com.br")
                .fullName("Ronaldo Macedo")
                .gender(GenderPerson.toEnum(1))
                .nationality("Brazilian")
                .placeOfBirth("Rio de Janeiro")
                .dateOfBirth(LocalDate.of(1930, 10, 10))
                .build();

        Person person2 = Person.builder()
                .id(2L)
                .cpf("55685220065")
                .email("maria@bol.com.br")
                .fullName("Maria Silva ")
                //.gender(1)
                .nationality("Brazilian")
                .placeOfBirth("Rio de Janeiro")
                .dateOfBirth(LocalDate.of(1930, 10, 10))
                .build();

        personRepository.saveAll(Arrays.asList(person1, person2));


    }
}
