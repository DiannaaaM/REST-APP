package ru.hogwarts.school.REST_APP.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.REST_APP.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final Map<Long, Student> students = new HashMap<>();
    private Long nextId = 1L;

    public Student createStudent(String name, int age) {
        Student student = new Student(nextId, name, age);
        students.put(nextId, student);
        nextId++;
        return student;
    }

    public Student findStudent(Long id) {
        return students.get(id);
    }

    public Student updateStudent(Long id, String name, int age) {
        Student student = new Student(id, name, age);
        students.put(id, student);
        return student;
    }

    public void deleteStudent(Long id) {
        students.remove(id);
    }

    public Collection<Student> findStudentsByAge(int age) {
        return students.values().stream()
                .filter(student -> student.getAge() == age)
                .collect( Collectors.toList());
    }
}
