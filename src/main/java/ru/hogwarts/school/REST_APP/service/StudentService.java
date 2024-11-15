package ru.hogwarts.school.REST_APP.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.REST_APP.model.Student;
import ru.hogwarts.school.REST_APP.repository.StudentRepository;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        return studentRepository.findById( Math.toIntExact( id ) ).orElse(null);
    }

    public Student updateStudent(Long id, Student student) {
        student.setId(id);
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById( Math.toIntExact( id ) );
    }

    public Collection<Student> findStudentsByAge(long age) {
        return studentRepository.findStudentByAge(age);
    }

    public Collection<Student> findByAgeBetween(long min, long max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    public Collection<Student> findByFaculty(String faculty) {
        return studentRepository.findByFaculty(faculty);
    }

    public List<Student> findAll(){
    return studentRepository.findAll();
    }
    public int findAverageAge(){
        return studentRepository.findAverageAge();
    }

    public List<Student> findTop5(){
        return studentRepository.findTop5();
    }
}