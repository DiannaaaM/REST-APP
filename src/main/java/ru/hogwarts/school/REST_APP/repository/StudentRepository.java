package ru.hogwarts.school.REST_APP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.REST_APP.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByName(String name);
    List<Student> findStudentByAge(Long age);
    List<Student> findStudentById(Long Id);
    List<Student> deleteStudentById(Long id);
    List<Student> createStudent(Long id, Student student);
    List<Student> updateStudentById(Long id, Student student);
    List<Student> findByAgeBetween(long min, long max);
    List<Student> findByFaculty(String faculty);
    @Query(value = "SELECT COUNT(*) FROM Student", nativeQuery = true)
    List<Student> findAll();
    @Query(value = "SELECT * FROM Student AVG(age)", nativeQuery = true)
    int findAverageAge();
    @Query(value = "SELECT * order by id FROM Student", nativeQuery = true)
    List<Student> findTop5();
}
