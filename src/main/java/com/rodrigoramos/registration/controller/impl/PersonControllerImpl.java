package com.rodrigoramos.registration.controller.impl;

import com.rodrigoramos.registration.controller.PersonController;
import com.rodrigoramos.registration.dto.PersonNewDTO;
import com.rodrigoramos.registration.mapper.PersonMapper;
import com.rodrigoramos.registration.model.Person;
import com.rodrigoramos.registration.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/register/person")
public class PersonControllerImpl implements PersonController {

    private final PersonService personService;
    private final PersonMapper personMapper;

    @Override
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody PersonNewDTO personNewDTO) {
        Person person = personMapper.toModel(personNewDTO);
        person = personService.save(person);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(person.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable Long id) {
        Person person = personService.findById(id);
        return ResponseEntity.ok().body(person);
    }
}
