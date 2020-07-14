package com.rodrigoramos.registration.controller;

import com.rodrigoramos.registration.dto.PersonNewDto;
import com.rodrigoramos.registration.model.Person;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(tags = "Person API")
public interface PersonController {
    @ApiOperation("Register new person.")
    ResponseEntity<Void> save(PersonNewDto personNewDTO);

    @ApiOperation("Find person by Id")
    ResponseEntity<Person> findById(Long id);

    @ApiOperation("Find all person")
    @ApiResponse(code = 200, message = "Retorna a lista de pessoa")
    ResponseEntity<List<Person>> findAll();

    @ApiOperation("Delete person based on primary key")
    ResponseEntity<Void> delete(Long id);
}

/*
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de pessoa"),
        @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
        @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
})*/
