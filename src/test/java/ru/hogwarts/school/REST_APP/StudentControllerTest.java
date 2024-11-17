package ru.hogwarts.school.REST_APP;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.hogwarts.school.REST_APP.controller.StudentController;
import ru.hogwarts.school.REST_APP.model.Faculty;
import ru.hogwarts.school.REST_APP.model.Student;
import ru.hogwarts.school.REST_APP.service.StudentService;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    public void testCreateStudent() throws Exception {
        Student student = new Student();
        student.setName("John Doe");
        student.setAge(20);

        when(studentService.createStudent(any(Student.class))).thenReturn(student);

        mockMvc.perform(post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\",\"age\":20}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.age").value(20));
    }

    @Test
    public void testFindStudent() throws Exception {
        Long studentId = 1L;
        Student student = new Student();
        student.setId(studentId);
        student.setName("John Doe");
        student.setAge(20);

        when(studentService.findStudent(studentId)).thenReturn(student);

        mockMvc.perform(get("/student/{id}", studentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.age").value(20));
    }

    @Test
    public void testUpdateStudent() throws Exception {
        Long studentId = 1L;
        Student student = new Student();
        student.setName("Jane Doe");
        student.setAge(25);

        when(studentService.updateStudent(any(Long.class), any(Student.class))).thenReturn(student);

        mockMvc.perform(put("/student/{id}", studentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Jane Doe\",\"age\":25}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Doe"))
                .andExpect(jsonPath("$.age").value(25));
    }

    @Test
    public void testDeleteStudent() throws Exception {
        Long studentId = 1L;

        mockMvc.perform(delete("/student/{id}", studentId))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testFindStudentsByAge() throws Exception {
        long age = 20;
        when(studentService.findStudentsByAge(age)).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/student/by-age?age={age}", age))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindByAgeBetween() throws Exception {
        long min = 18;
        long max = 25;
        when(studentService.findByAgeBetween(min, max)).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/student/by-age-between?min={min}&max={max}", min, max))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindByFaculty() throws Exception {
        Long facultyId = 1L;
        when(studentService.findByFaculty(any( Faculty.class))).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/student/by-faculty?facultyId={facultyId}", facultyId))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindAll() throws Exception {
        when(studentService.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/student/all"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetTop5() throws Exception {
        when(studentService.findTop5()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/student/top5"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindAverageAge() throws Exception {
        when(studentService.findAverageAge()).thenReturn(20.0);

        mockMvc.perform(get("/student/average-age"))
                .andExpect(status().isOk())
                .andExpect(content().string("20.0"));
    }
}
