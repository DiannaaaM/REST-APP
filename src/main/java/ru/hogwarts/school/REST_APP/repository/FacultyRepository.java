package ru.hogwarts.school.REST_APP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.hogwarts.school.REST_APP.model.Faculty;
import ru.hogwarts.school.REST_APP.model.Student;

import java.util.Collection;
import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
    Collection<Faculty> findByColor(String color);

    @Query("SELECT f FROM Faculty f WHERE LOWER(f.name) LIKE LOWER(CONCAT('%', :name, '%')) AND f.color = :color")
    Collection<Faculty> findFacultiesByColorAndNameIgnoreCase(@Param("name") String name, @Param("color") String color);

    @Query("SELECT s FROM Student s WHERE s.faculty.id = :facultyId")
    Collection<Student> findStudentsInFaculty(@Param("facultyId") Long facultyId);


}
