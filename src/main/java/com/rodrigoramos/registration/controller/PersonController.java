package com.rodrigoramos.registration.controller;

import com.rodrigoramos.registration.dto.PersonNewDTO;
import com.rodrigoramos.registration.model.Person;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

@Api(tags = "Person API")
public interface PersonController {
    @ApiOperation("Register new person.")
    ResponseEntity<Void> save(PersonNewDTO personNewDTO);

    @ApiOperation("Find person by Id")
    ResponseEntity<Person> findById(Long id);
}
