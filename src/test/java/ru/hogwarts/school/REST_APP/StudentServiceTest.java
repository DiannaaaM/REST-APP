package ru.hogwarts.school.REST_APP;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.REST_APP.model.Student;
import ru.hogwarts.school.REST_APP.service.StudentService;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentService();
    }

    @Test
    void createStudent() {
        Student student = studentService.createStudent("Harry Potter", 17);
        assertEquals(1L, student.getId());
        assertEquals("Harry Potter", student.getName());
        assertEquals(17, student.getAge());
    }

    @Test
    void findStudent() {
        studentService.createStudent("Harry Potter", 17);
        Student foundStudent = studentService.findStudent(1L);
        assertNotNull(foundStudent);
        assertEquals("Harry Potter", foundStudent.getName());
        assertEquals(17, foundStudent.getAge());
    }

    @Test
    void findStudent_NotFound() {
        Student foundStudent = studentService.findStudent(1L);
        assertNull(foundStudent);
    }

    @Test
    void updateStudent() {
        studentService.createStudent("Harry Potter", 17);
        Student updatedStudent = studentService.updateStudent(1L, "Hermione Granger", 18);
        assertEquals(1L, updatedStudent.getId());
        assertEquals("Hermione Granger", updatedStudent.getName());
        assertEquals(18, updatedStudent.getAge());
    }

    @Test
    void findStudentsByAge() {
        studentService.createStudent("Harry Potter", 17);
        studentService.createStudent("Ron Weasley", 17);
        studentService.createStudent("Hermione Granger", 18);

        Collection<Student> students17 = studentService.findStudentsByAge(17);
        assertEquals(2, students17.size());

        Collection<Student> students18 = studentService.findStudentsByAge(18);
        assertEquals(1, students18.size());

        Collection<Student> students20 = studentService.findStudentsByAge(20);
        assertEquals(0, students20.size());
    }
}
