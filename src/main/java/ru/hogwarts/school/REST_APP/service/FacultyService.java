package ru.hogwarts.school.REST_APP.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.REST_APP.model.Faculty;
import ru.hogwarts.school.REST_APP.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final Map<Long, Faculty> faculties = new HashMap<>();
    private Long nextId = 1L;

    public Faculty createFaculty(String name, String color) {
        Faculty faculty = new Faculty(nextId, name, color);
        faculties.put(nextId, faculty);
        nextId++;
        return faculty;
    }

    public Faculty findFaculty(Long id) {
        return faculties.get(id);
    }

    public Faculty updateFaculty(Long id, String name, String color) {
        Faculty faculty = new Faculty(id, name, color);
        faculties.put(id, faculty);
        return faculty;
    }

    public void deleteFaculty(Long id) {
        faculties.remove(id);
    }

    public Collection<Faculty> findFacultiesByColor(String color) {
        return faculties.values().stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .collect( Collectors.toList());
    }
}