package ru.hogwarts.school.REST_APP;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import ru.hogwarts.school.REST_APP.model.Faculty;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTest{

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateFaculty() {
        Faculty faculty = new Faculty();
        faculty.setName("Gryffindor");
        faculty.setColor("Red");

        HttpEntity<Faculty> request = new HttpEntity<>(faculty);
        ResponseEntity<Faculty> response = restTemplate.exchange("/faculty", HttpMethod.POST, request, Faculty.class);

        assertEquals(HttpStatus.valueOf( 415 ), response.getStatusCode());
    }

    @Test
    public void testFindFaculty() {
        Long facultyId = 1L;
        ResponseEntity<Faculty> response = restTemplate.getForEntity("/faculty/{id}", Faculty.class, facultyId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testUpdateFaculty() {
        Long facultyId = 1L;
        Faculty faculty = new Faculty();
        faculty.setName("Slytherin");
        faculty.setColor("Green");

        HttpEntity<Faculty> request = new HttpEntity<>(faculty);
        ResponseEntity<Faculty> response = restTemplate.exchange("/faculty/{id}", HttpMethod.PUT, request, Faculty.class, facultyId);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testDeleteFaculty() {
        Long facultyId = 1L; // Assuming a faculty with ID 1 exists
        ResponseEntity<Void> response = restTemplate.exchange("/faculty/{id}", HttpMethod.DELETE, null, Void.class, facultyId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testFindFacultiesByColor() {
        String color = "Red";
        ResponseEntity<Collection> response = restTemplate.getForEntity("/faculty/by-color?color={color}", Collection.class, color);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}