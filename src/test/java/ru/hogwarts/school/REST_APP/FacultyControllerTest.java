package ru.hogwarts.school.REST_APP;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.hogwarts.school.REST_APP.controller.FacultyController;
import ru.hogwarts.school.REST_APP.model.Faculty;
import ru.hogwarts.school.REST_APP.model.Student;
import ru.hogwarts.school.REST_APP.service.FacultyService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FacultyController.class)
class FacultyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyService facultyService;

    @Test
    void createFaculty() throws Exception {
        List<Student> students = Arrays.asList(new Student(1L, "Harry Potter", 11, "Gryffindor"),
                new Student(2L, "Ron Weasley", 11,"Gryffindor"));
        Faculty expectedFaculty = new Faculty(1L, "Gryffindor", "Red", students);
        when(facultyService.createFaculty(expectedFaculty)).thenReturn(expectedFaculty);

        mockMvc.perform(MockMvcRequestBuilders.post("/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "id": 1,
                            "name": "Gryffindor",
                            "color": "Red"
                        }
                        """))
                .andExpect(status().is(415));
    }

    @Test
    void findFaculty() throws Exception {
        List<Student> students = Arrays.asList(new Student(1L, "Harry Potter", 11, "Gryffindor"),
                new Student(2L, "Ron Weasley", 11,"Gryffindor"));
        Faculty expectedFaculty = new Faculty(1L, "Gryffindor", "Red", students);
        when(facultyService.findFaculty(1L)).thenReturn(expectedFaculty);

        mockMvc.perform(MockMvcRequestBuilders.get("/faculty/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Gryffindor"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.color").value("Red"));
    }

    @Test
    void findFacultyNotFound() throws Exception {
        when(facultyService.findFaculty(1L)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/faculty/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateFaculty() throws Exception {
        List<Student> students = Arrays.asList(new Student(1L, "Harry Potter", 11, "Gryffindor"),
                new Student(2L, "Ron Weasley", 11,"Gryffindor"));
        Faculty updatedFaculty = new Faculty(1L, "Gryffindor", "Scarlet", students);
        when(facultyService.updateFaculty(1L, updatedFaculty)).thenReturn(updatedFaculty);

        mockMvc.perform(MockMvcRequestBuilders.put("/faculty/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "id": 1,
                            "name": "Gryffindor",
                            "color": "Scarlet"
                        }
                        """))
                .andExpect(status().isOk());
    }

    @Test
    void deleteFaculty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/faculty/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void findFacultiesByColor() throws Exception {
        List<Student> students = Arrays.asList(new Student(1L, "Harry Potter", 11, "Gryffindor"),
                new Student(2L, "Ron Weasley", 11,"Gryffindor"));
        List<Student> studentsHu = new ArrayList<>(students);
        Collection<Faculty> expectedFaculties = Arrays.asList(
                new Faculty(1L, "Gryffindor", "Red", students),
                new Faculty(2L, "Hufflepuff", "Yellow", studentsHu)
        );
        when(facultyService.findFacultiesByColor("Red")).thenReturn(expectedFaculties);

        mockMvc.perform(MockMvcRequestBuilders.get("/faculty/by-color?color=Red"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Gryffindor"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].color").value("Red"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Hufflepuff"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].color").value("Yellow"));
    }

    @Test
    void findFacultiesByColorAndNameIgnoreCase() throws Exception {
        List<Student> students = Arrays.asList(new Student(1L, "Harry Potter", 11, "Gryffindor"),
                new Student(2L, "Ron Weasley", 11,"Gryffindor"));
        List<Student> studentsHu = new ArrayList<>(students);
        Collection<Faculty> expectedFaculties = Arrays.asList(
                new Faculty(1L, "Gryffindor", "Red", students),
                new Faculty(2L, "hufflepuff", "Red", studentsHu)
        );
        when(facultyService.findFacultiesByColorAndNameIgnoreCase("Gryffindor", "Red")).thenReturn(expectedFaculties);

        mockMvc.perform(MockMvcRequestBuilders.get("/faculty/Gryffindor--Red"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void findStudentsInFaculty() throws Exception {
        List<Student> students = Arrays.asList(new Student(1L, "Harry Potter", 11, "Gryffindor"),
                new Student(2L, "Ron Weasley", 11,"Gryffindor"));
        List<Student> studentsHu = new ArrayList<>(students);
        Collection<Faculty> expectedFaculties = Arrays.asList(
                new Faculty(1L, "Gryffindor", "Red", students),
                new Faculty(2L, "Hufflepuff", "Yellow", studentsHu)
        );
        when(facultyService.findStudentsInFaculty("Gryffindor")).thenReturn(expectedFaculties);

        mockMvc.perform(MockMvcRequestBuilders.get("/faculty/Gryffindor-students"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Gryffindor"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].color").value("Red"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Hufflepuff"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].color").value("Yellow"));
    }

}
