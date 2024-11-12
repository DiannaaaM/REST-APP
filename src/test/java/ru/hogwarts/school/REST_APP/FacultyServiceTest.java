package ru.hogwarts.school.REST_APP;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.REST_APP.model.Faculty;
import ru.hogwarts.school.REST_APP.service.FacultyService;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class FacultyServiceTest {

    private FacultyService facultyService;

    @BeforeEach
    void setUp() {
        facultyService = new FacultyService();
    }

    @Test
    void createFaculty() {
        Faculty faculty = facultyService.createFaculty("Gryffindor", "Red");
        assertEquals(1L, faculty.getId());
        assertEquals("Gryffindor", faculty.getName());
        assertEquals("Red", faculty.getColor());
    }

    @Test
    void findFaculty() {
        facultyService.createFaculty("Gryffindor", "Red");
        Faculty foundFaculty = facultyService.findFaculty(1L);
        assertNotNull(foundFaculty);
        assertEquals("Gryffindor", foundFaculty.getName());
        assertEquals("Red", foundFaculty.getColor());
    }

    @Test
    void findFaculty_NotFound() {
        Faculty foundFaculty = facultyService.findFaculty(1L);
        assertNull(foundFaculty);
    }

    @Test
    void updateFaculty() {
        facultyService.createFaculty("Gryffindor", "Red");
        Faculty updatedFaculty = facultyService.updateFaculty(1L, "Hufflepuff", "Yellow");
        assertEquals(1L, updatedFaculty.getId());
        assertEquals("Hufflepuff", updatedFaculty.getName());
        assertEquals("Yellow", updatedFaculty.getColor());
    }

    @Test
    void deleteFaculty() {
        facultyService.createFaculty("Gryffindor", "Red");
        facultyService.deleteFaculty(1L);
    }

    @Test
    void findFacultiesByColor() {
        facultyService.createFaculty("Gryffindor", "Red");
        facultyService.createFaculty("Hufflepuff", "Yellow");
        facultyService.createFaculty("Ravenclaw", "Blue");

        Collection<Faculty> redFaculties = facultyService.findFacultiesByColor("Red");
        assertEquals(1, redFaculties.size());

        Collection<Faculty> yellowFaculties = facultyService.findFacultiesByColor("Yellow");
        assertEquals(1, yellowFaculties.size());

        Collection<Faculty> blueFaculties = facultyService.findFacultiesByColor("Blue");
        assertEquals(1, blueFaculties.size());

        Collection<Faculty> nonExistentFaculties = facultyService.findFacultiesByColor("Green");
        assertEquals(0, nonExistentFaculties.size());
    }
}
