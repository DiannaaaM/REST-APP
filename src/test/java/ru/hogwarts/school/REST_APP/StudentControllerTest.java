package ru.hogwarts.school.REST_APP;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import ru.hogwarts.school.REST_APP.model.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
@TestPropertySource(properties = {
        "spring.datasource.username=student" +
                "spring.datasource.password=chocolatefrog" +
                "spring.datasource.url=jdbc:postgresql://localhost:5432/Hogwarts"
})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void createStudent() {
        Student student = new Student(null, "Harry Potter", 11, "Gryffindor");
        ResponseEntity<Student> response = restTemplate.postForEntity("/student", student, Student.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
    }

    @Test
    void findStudent() {
        ResponseEntity<Student> response = restTemplate.getForEntity("/student/1", Student.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void findStudentNotFound() {
        ResponseEntity<Student> response = restTemplate.getForEntity("/student/999", Student.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteStudent() {
        ResponseEntity<Void> response = restTemplate.exchange("/student/1", HttpMethod.DELETE, null, Void.class);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void findStudentsByAge() {
        ResponseEntity<Student[]> response = restTemplate.getForEntity("/student/by-age?age=11", Student[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void findByAgeBetween() {
        ResponseEntity<Student[]> response = restTemplate.getForEntity("/student/11-12", Student[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void findByFaculty() {
        ResponseEntity<Student[]> response = restTemplate.getForEntity("/student/Gryffindor-student", Student[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
