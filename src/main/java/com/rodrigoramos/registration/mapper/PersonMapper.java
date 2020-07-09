package com.rodrigoramos.registration.mapper;

import com.rodrigoramos.registration.dto.PersonNewDTO;
import com.rodrigoramos.registration.model.Person;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PersonMapper {
    private final ModelMapper modelMapper;

    public Person toModel(PersonNewDTO personNewDTO) {
        return modelMapper.map(personNewDTO, Person.class);
    }

}
