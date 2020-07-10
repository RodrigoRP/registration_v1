package com.rodrigoramos.registration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodrigoramos.registration.dto.PersonNewDTO;
import com.rodrigoramos.registration.mapper.PersonMapper;
import com.rodrigoramos.registration.model.Person;
import com.rodrigoramos.registration.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class PersonControllerTest {

    final String BASE_URL = "/api/v1/register/person";

    @MockBean
    PersonServiceImpl service;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PersonMapper personMapper;

    PersonNewDTO personNewDTO;
    Person person;

    @BeforeEach
    void setUp() {
        personNewDTO = PersonNewDTO.builder()
                .cpf("73512731031")
                //.dateOfBirth(LocalDate.of(2000, 12, 12))
                .email("adamastor@bol.com.br")
                .fullName("Adamastor Silva")
                .gender("Masculino")
                .nationality("Brazilian")
                .placeOfBirth("São Paulo")
                .build();

        person = personMapper.toModel(personNewDTO);
    }

    @Test
    @DisplayName("POST /api/v1/register/person")
    void savePersonTest() throws Exception {
        doReturn(person).when(service).save(any());

        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(personNewDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("GET /api/v1/register/person/1")
    void findPersonByIdTest() throws Exception {
        doReturn(person).when(service).findById(1L);

        mockMvc.perform(get(BASE_URL + "/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.fullName", is("Adamastor Silva")))
                .andExpect(jsonPath("$.nationality", is("Brazilian")));
    }


    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
