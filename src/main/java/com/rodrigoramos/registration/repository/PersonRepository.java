package com.rodrigoramos.registration.repository;

import com.rodrigoramos.registration.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Transactional(readOnly = true)
    Optional<Person> findByEmail(String email);

    @Transactional(readOnly = true)
    Optional<Person> findByCpf(String cpf);
}
