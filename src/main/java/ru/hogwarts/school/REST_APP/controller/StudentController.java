package ru.hogwarts.school.REST_APP.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.REST_APP.model.Faculty;
import ru.hogwarts.school.REST_APP.model.Student;
import ru.hogwarts.school.REST_APP.service.StudentService;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        if (student.getAge() == null) {
            student.setAge(0); // Ensure age is not null
        }
        Student created = studentService.createStudent(student);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findStudent(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        if (student.getAge() == null) {
            student.setAge(0); // Ensure age is not null
        }
        Student updated = studentService.updateStudent(id, student);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/by-age")
    public ResponseEntity<Collection<Student>> findStudentsByAge(@RequestParam long age) {
        return new ResponseEntity<>(studentService.findStudentsByAge(age), HttpStatus.OK);
    }

    @GetMapping("/by-age-between")
    public ResponseEntity<Collection<Student>> findByAgeBetween(@RequestParam long min, @RequestParam long max) {
        return new ResponseEntity<>(studentService.findByAgeBetween(min, max), HttpStatus.OK);
    }

    @GetMapping("/by-faculty")
    public ResponseEntity<Collection<Student>> findByFaculty(@RequestParam Long facultyId) {
        Faculty faculty = new Faculty();
        faculty.setId(facultyId);
        return new ResponseEntity<>(studentService.findByFaculty(faculty), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> findAll() {
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/top5")
    public ResponseEntity<List<Student>> getTop5() {
        return new ResponseEntity<>(studentService.findTop5(), HttpStatus.OK);
    }

    @GetMapping("/average-age")
    public ResponseEntity<Double> findAverageAge() {
        return new ResponseEntity<>(studentService.findAverageAge(), HttpStatus.OK);
    }
}