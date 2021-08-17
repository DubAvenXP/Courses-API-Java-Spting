package com.company.backendcourses.web.controllers;

import com.company.backendcourses.persistence.entity.Course;
import com.company.backendcourses.persistence.entity.Student;
import com.company.backendcourses.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/student/{id}")
    public ResponseEntity<Student> getCourse(@PathVariable String id) {
        return ResponseEntity.of(studentService.getStudent(id));
    }

    @GetMapping(value = "/student")
    public ResponseEntity<List<Student>> getCourse() {
        return new ResponseEntity<>(studentService.getStudents(), HttpStatus.OK);
    }

    @PostMapping(value = "/student")
    public ResponseEntity<Student> createCourse(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.createStudent(student), HttpStatus.CREATED);
    }

    @PutMapping(value = "/student/{id}")
    public ResponseEntity<Student> updateCourse(@RequestBody Student student, @PathVariable String id) {
        return ResponseEntity.of(studentService.updateStudent(student, id));
    }

    @DeleteMapping(value = "/student/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable String id) {
        if (studentService.deleteCourse(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
