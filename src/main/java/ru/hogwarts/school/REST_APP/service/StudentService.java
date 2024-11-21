package ru.hogwarts.school.REST_APP.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.REST_APP.model.Faculty;
import ru.hogwarts.school.REST_APP.model.Student;
import ru.hogwarts.school.REST_APP.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.Collections.max;
import static java.util.Collections.min;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final Student student;

    Logger logger = Logger.getLogger(StudentService.class.getName());

    @Autowired
    public StudentService(StudentRepository studentRepository, Student student) {
        this.studentRepository = studentRepository;
        this.student = student;
    }

    public Student createStudent(Student student) {
        logger.info( "Creating new student " + student.getName() );
        if (student.getAge() == null) {
            student.setAge(0); // Ensure age is not null
        }
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        logger.info( "Finding student by Id" + id );
        return studentRepository.findById(id).orElse(null);
    }

    public Student updateStudent(Long id, Student student) {
        logger.info( "Updating student " + id + ", name: " + student.getName() );
        student.setId(id);
        if (student.getAge() == null) {
            student.setAge(0); // Ensure age is not null
        }
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        logger.info( "Deleting student by Id" + id );
        studentRepository.deleteById(id);
    }

    public Collection<Student> findStudentsByName(String name) {
        logger.info( "Finding students by name " + name );
        return studentRepository.findByName(name);
    }

    public Collection<Student> findStudentsByAge(Long age) {
        logger.info( "Finding students by age " + age );
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(long min, long max) {
        logger.info( "Finding students by age between " + min + " and " + max );
        return studentRepository.findByAgeBetween(min, max);
    }

    public Collection<Student> findByFaculty(Faculty faculty) {
        logger.info( "Finding students by faculty " + faculty );
        return studentRepository.findByFaculty(faculty);
    }

    public List<Student> findAll() {
        logger.info( "Finding all students" );
        return studentRepository.findAll();
    }

    public Double findAverageAge() {
        logger.info( "Finding average age of students" );
        return studentRepository.findAverageAge();
    }

    public List<Student> findTop5() {
        logger.info( "Finding top 5 students" );
        return studentRepository.findTop5();
    }

    public List<Student> findNamesOfStudentsA(){
        logger.info( "Finding names of students A" );
        return findAll().stream()
                .filter(student -> Objects.equals( student.getName(), "А%" ) )
                .toList();

    }

    public double findAveragesAge(){
        logger.info( "Finding average age of students" );
        return  findAll().stream()
                .collect(Collectors.averagingDouble(Student::getAge));
    }
}