package ru.hogwarts.school.REST_APP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.REST_APP.model.Faculty;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
    List<Faculty> findByColor(String color);
    List<Faculty> findFaculty(Long id);
    List<Faculty> deleteFacultyById(Long id);
    List<Faculty> createFaculity(Long id, Faculty faculty);
    List<Faculty> updateFacultyById(Long id, Faculty faculty);


}
