package com.rodrigoramos.registration.controller.impl;

import com.rodrigoramos.registration.controller.PersonController;
import com.rodrigoramos.registration.dto.PersonNewDto;
import com.rodrigoramos.registration.dto.PersonUpdateDto;
import com.rodrigoramos.registration.mapper.PersonMapper;
import com.rodrigoramos.registration.model.Person;
import com.rodrigoramos.registration.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.Authentication;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/register/person")
public class PersonControllerImpl implements PersonController {

    private final PersonService personService;
    private final PersonMapper personMapper;

    @Override
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody PersonNewDto personNewDTO) {
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

    @Override
    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<Person>> findAll() {
        List<Person> personList = personService.findAll();
        return ResponseEntity.ok().body(personList);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/redirect")
    public ResponseEntity<Object> redirectToExternalUrl() throws URISyntaxException {
        String urlString = "https://github.com/RodrigoRP/registration";
        URI githubUrl = new URI(urlString);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(githubUrl);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @RequestBody PersonUpdateDto personUpdateDto) {
        Person updatedPerson = personService.update(personUpdateDto, id);
        return ResponseEntity.ok().body(updatedPerson);
    }
/*
    @GetMapping("/username")
    @ResponseBody
    public String currentUserName(Authentication authentication) {
        return authentication.getName();
    }*/
}
