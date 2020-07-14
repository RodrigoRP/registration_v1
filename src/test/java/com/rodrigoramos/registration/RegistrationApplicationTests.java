package com.rodrigoramos.registration;

import com.rodrigoramos.registration.service.impl.PersonServiceImpl;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RegistrationApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private PersonServiceImpl personService;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.basePath = "/api/v1/register/person";
    }

    @Test
    void should_Return_Status200_WhenGetPerson() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void should_Return_Person_WhenGetByID() {
        given()
                .pathParam("id", 1)
                .accept(ContentType.JSON)
                .when()
                .get("/{id}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("fullName", equalTo("Ronaldo Macedo"));
    }

    @Test
    void should_return_404_getById() {
        given()
                .pathParam("id", 99)
                .accept(ContentType.JSON)
                .when()
                .get("/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void should_Return_Status201_WhenCreatePerson() {
        String personNew = "{ \"fullName\": \"Jonas\",\n" +
                "    \"gender\": \"MALE\",\n" +
                "    \"email\": \"ronasl4d1o@bol.com.br\",\n" +
                "    \"dateOfBirth\": \"2000-10-10\",\n" +
                "    \"placeOfBirth\": \"Rio de Janeiro\",\n" +
                "    \"nationality\": \"Brazilian\",\n" +
                "    \"cpf\": \"66375288037\" }";

        given()
                .body(personNew)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    void deleteByID() {
        given()
                .accept(ContentType.JSON)
                .pathParam("id", 1)
                .when()
                .delete("/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void deleteNotFoundByID() {
        given()
                .accept(ContentType.JSON)
                .pathParam("id", 11)
                .when()
                .delete("/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void updatePersonByID() {
        String personUpdated = "{\n" +
                "  \"fullName\": \"Ronaldo Assis\"\n" +
                "}";
        given()
                .body(personUpdated)
                .contentType(ContentType.JSON)
                .pathParam("id", 1)
                .when()
                .patch("/{id}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("fullName", equalTo("Ronaldo Assis"));
    }

    @Test
    void testRedirectPage() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/redirect")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

}
