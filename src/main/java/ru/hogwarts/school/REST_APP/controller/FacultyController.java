package ru.hogwarts.school.REST_APP.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.REST_APP.model.Faculty;
import ru.hogwarts.school.REST_APP.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        Faculty created = facultyService.createFaculty(faculty);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> findFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty != null) {
            return new ResponseEntity<>(faculty, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faculty> updateFaculty(@PathVariable Long id, @RequestBody Faculty faculty) {
        Faculty updated = facultyService.updateFaculty(id, faculty);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/by-color")
    public ResponseEntity<Collection<Faculty>> findFacultiesByColor(@RequestParam String color) {
        return new ResponseEntity<>(facultyService.findFacultiesByColor(color), HttpStatus.OK);
    }

    @GetMapping("/{name}--{color}")
    public ResponseEntity<Collection<Faculty>> findFacultiesByColorAndNameIgnoreCase(@PathVariable String name, @PathVariable String color) {
        return new ResponseEntity<>(facultyService.findFacultiesByColorAndNameIgnoreCase(name, color), HttpStatus.OK);
    }

    @GetMapping("/{faculty}-students")
    public ResponseEntity<Collection<Faculty>> findFacultiesByFaculty(@PathVariable String faculty) {
        return new ResponseEntity<>(facultyService.findStudentsInFaculty(faculty), HttpStatus.OK);
    }
}
