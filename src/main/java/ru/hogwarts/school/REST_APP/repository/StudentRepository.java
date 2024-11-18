package ru.hogwarts.school.REST_APP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.REST_APP.model.Faculty;
import ru.hogwarts.school.REST_APP.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByName(String name);
    List<Student> findByAge(Long age);
    List<Student> findByAgeBetween(long min, long max);
    List<Student> findByFaculty(Faculty faculty);

    @Query(value = "SELECT AVG(age) FROM students", nativeQuery = true)
    Double findAverageAge();

    @Query(value = "SELECT * FROM students ORDER BY id LIMIT 5", nativeQuery = true)
    List<Student> findTop5();
}
