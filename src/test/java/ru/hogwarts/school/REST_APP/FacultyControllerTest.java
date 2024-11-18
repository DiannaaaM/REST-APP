package ru.hogwarts.school.REST_APP;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.hogwarts.school.REST_APP.controller.FacultyController;
import ru.hogwarts.school.REST_APP.model.Faculty;
import ru.hogwarts.school.REST_APP.service.FacultyService;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FacultyController.class)
public class FacultyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyService facultyService;

    @Test
    public void testCreateFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("Gryffindor");
        faculty.setColor("Red");

        when(facultyService.createFaculty(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(post("/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Gryffindor\",\"color\":\"Red\"}"))
                .andExpect(status().is(415));
    }

    @Test
    public void testFindFaculty() throws Exception {
        Long facultyId = 1L;
        Faculty faculty = new Faculty();
        faculty.setId(facultyId);
        faculty.setName("Gryffindor");
        faculty.setColor("Red");

        when(facultyService.findFaculty(facultyId)).thenReturn(faculty);

        mockMvc.perform(get("/faculty/{id}", facultyId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Gryffindor"))
                .andExpect(jsonPath("$.color").value("Red"));
    }

    @Test
    public void testUpdateFaculty() throws Exception {
        Long facultyId = 1L;
        Faculty faculty = new Faculty();
        faculty.setName("Slytherin");
        faculty.setColor("Green");

        when(facultyService.updateFaculty(any(Long.class), any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(put("/faculty/{id}", facultyId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Slytherin\",\"color\":\"Green\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Slytherin"))
                .andExpect(jsonPath("$.color").value("Green"));
    }

    @Test
    public void testDeleteFaculty() throws Exception {
        Long facultyId = 1L;

        mockMvc.perform(delete("/faculty/{id}", facultyId))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testFindFacultiesByColor() throws Exception {
        String color = "Red";
        when(facultyService.findFacultiesByColor(color)).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/faculty/by-color?color={color}", color))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindFacultiesByColorAndNameIgnoreCase() throws Exception {
        String name = "Gryffindor";
        String color = "Red";
        when(facultyService.findFacultiesByColorAndNameIgnoreCase(name, color)).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/faculty/{name}--{color}", name, color))
                .andExpect(status().is(400));
    }

    @Test
    public void testFindFacultiesByFaculty() throws Exception {
        String faculty = "Gryffindor";
        when(facultyService.findStudentsInFaculty(faculty)).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/faculty/{faculty}-students", faculty))
                .andExpect(status().isOk());
    }
}
