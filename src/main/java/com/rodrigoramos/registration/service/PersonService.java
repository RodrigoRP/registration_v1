package com.rodrigoramos.registration.service;

import com.rodrigoramos.registration.dto.PersonUpdateDto;
import com.rodrigoramos.registration.model.Person;

public interface PersonService extends GenericService<Person, Long> {

    Person update(PersonUpdateDto personUpdateDto, Long id);
}
