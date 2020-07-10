package com.rodrigoramos.registration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodrigoramos.registration.dto.PersonNewDTO;
import com.rodrigoramos.registration.mapper.PersonMapper;
import com.rodrigoramos.registration.model.Person;
import com.rodrigoramos.registration.service.exception.ObjectNotFoundException;
import com.rodrigoramos.registration.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
                //.gender("Male")
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

    @Test
    @DisplayName("GET /api/v1/register/person/2")
    void findPersonByIdNotFoundTest() throws Exception {
        // given
        given(service.findById(2L))
                .willThrow(new ObjectNotFoundException("Not found"));

        // when
        MockHttpServletResponse response = mockMvc.perform(
                get(BASE_URL + "/{id}", 2)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("GET /api/v1/register/person/")
    void findAllPersonTest() throws Exception {
        // given
        List<Person> personList = new ArrayList<>();
        personList.add(person);

        // when
        when(service.findAll()).thenReturn(personList);
        MockHttpServletResponse response = mockMvc.perform(
                get(BASE_URL + "/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].fullName", is("Adamastor Silva")))
                .andExpect(jsonPath("$[0].cpf", is("73512731031")))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void deletePersonByIdTest() throws Exception {
        person.setId(1L);
        //when
        when(service.findById(1L)).thenReturn(person);

        //then
        mockMvc.perform(delete(BASE_URL + "/{id}", 1))
                .andExpect(status().isNoContent());

        verify(service, times(1)).deleteById(1L);
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
