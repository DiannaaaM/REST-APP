package ru.hogwarts.school.REST_APP.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
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
        Student updated = studentService.updateStudent(id, student);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/by-age")
    public ResponseEntity<Collection<Student>> findStudentsByAge(@RequestParam int age) {
        return new ResponseEntity<>(studentService.findStudentsByAge(age), HttpStatus.OK);
    }

    @GetMapping("/{min}-{max}")
    public ResponseEntity<Collection<Student>> findByAgeBetween(@PathVariable int min, @PathVariable int max) {
        return new ResponseEntity<>(studentService.findByAgeBetween( min, max ), HttpStatus.OK);
    }
    @GetMapping("{faculty}-student")
    public ResponseEntity<Collection<Student>> findByFaculty(@PathVariable String faculty) {
        return new ResponseEntity<>(studentService.findByFaculty(faculty), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Integer> findAll() {
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getTop5")
    public ResponseEntity<List<Student>> getTop5(){
        return new ResponseEntity<>(studentService.findTop5(), HttpStatus.OK);
    }

    @GetMapping("/fingAverageAge")
    public ResponseEntity<Integer> findAverageAge() {
        return new ResponseEntity<>(studentService.findAverageAge(), HttpStatus.OK);
    }
}