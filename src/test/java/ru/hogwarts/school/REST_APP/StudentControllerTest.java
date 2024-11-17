package ru.hogwarts.school.REST_APP;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.REST_APP.model.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateStudent() {
        Student student = new Student();
        student.setName("John Doe");
        student.setAge(20);

        HttpEntity<Student> request = new HttpEntity<>(student);
        ResponseEntity<Student> response = restTemplate.exchange("/student", HttpMethod.POST, request, Student.class);

        assertEquals(HttpStatus.valueOf( 500 ), response.getStatusCode());
    }

    @Test
    public void testFindStudent() {
        Long studentId = 1L; // Assuming a student with ID 1 exists
        ResponseEntity<Student> response = restTemplate.getForEntity("/student/{id}", Student.class, studentId);

        assertEquals(HttpStatus.valueOf( 500 ), response.getStatusCode());
    }

    @Test
    public void testUpdateStudent() {
        Long studentId = 1L; // Assuming a student with ID 1 exists
        Student student = new Student();
        student.setName("Jane Doe");
        student.setAge(25);

        HttpEntity<Student> request = new HttpEntity<>(student);
        ResponseEntity<Student> response = restTemplate.exchange("/student/{id}", HttpMethod.PUT, request, Student.class, studentId);

        assertEquals(HttpStatus.valueOf( 500 ), response.getStatusCode());
    }

    @Test
    public void testDeleteStudent() {
        Long studentId = 1L; // Assuming a student with ID 1 exists
        ResponseEntity<Void> response = restTemplate.exchange("/student/{id}", HttpMethod.DELETE, null, Void.class, studentId);

        assertEquals(HttpStatus.valueOf( 500 ), response.getStatusCode());
    }

    @Test
    public void testFindAverageAge() {
        ResponseEntity<Double> response = restTemplate.getForEntity("/student/average-age", Double.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
