package ru.hogwarts.school.REST_APP.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.REST_APP.model.Faculty;
import ru.hogwarts.school.REST_APP.model.Student;
import ru.hogwarts.school.REST_APP.repository.FacultyRepository;

import java.util.Collection;
import java.util.logging.Logger;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    Logger logger = Logger.getLogger(FacultyService.class.getName());

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Creating faculty: " + faculty);
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(Long id) {
        logger.info("Finding faculty: " + id);
        return facultyRepository.findById(Math.toIntExact(id)).orElse(null);
    }

    public Faculty updateFaculty(Long id, Faculty faculty) {
        logger.info("Updating faculty: " + faculty);
        faculty.setId(id);
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long id) {
        logger.info("Deleting faculty: " + id);
        facultyRepository.deleteById(Math.toIntExact(id));
    }

    public Collection<Faculty> findFacultiesByColor(String color) {
        logger.info("Finding faculties by color: " + color);
        return facultyRepository.findByColor(color);
    }

    public Collection<Faculty> findFacultiesByColorAndNameIgnoreCase(String name, String color) {
        logger.info("Finding faculties by color and name: " + name + " and color: " + color);
        return findFacultiesByColorAndNameIgnoreCase(name, color);
    }

    public Collection<Faculty> findStudentsInFaculty(String faculty) {
        logger.info("Finding students in faculty: " + faculty);
        return findStudentsInFaculty(faculty);
    }
}
