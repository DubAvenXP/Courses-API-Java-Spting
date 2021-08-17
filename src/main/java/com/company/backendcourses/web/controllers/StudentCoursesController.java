package com.company.backendcourses.web.controllers;

import com.company.backendcourses.persistence.entity.StudentCourses;
import com.company.backendcourses.service.StudentCoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class StudentCoursesController {

    @Autowired
    private StudentCoursesService service;

    @GetMapping(value = "/studentcourses/{id}")
    public ResponseEntity<List<StudentCourses>> getStudentCourses(@PathVariable String id) {
        return new ResponseEntity<>(service.getStudentCourses(id), HttpStatus.OK);
    }

    @GetMapping(value = "/studentsbycourse/{id}")
    public ResponseEntity<List<StudentCourses>> getStudentsByCourse(@PathVariable Integer id) {
        return new ResponseEntity<>(service.getStudentsByCourse(id), HttpStatus.OK);
    }

    @GetMapping(value = "/studentcourses")
    public ResponseEntity<List<StudentCourses>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/studentcourses")
    public ResponseEntity<StudentCourses> createStudentCourse(@RequestBody StudentCourses studentCourse) {
        return new ResponseEntity<>(service.createStudentCourses(studentCourse), HttpStatus.CREATED);
    }

    @PutMapping(value = "/studentcourses/{id}")
    public ResponseEntity<StudentCourses> updateStudentCourses(@RequestBody StudentCourses studentCourse, @PathVariable Integer id) {
        return ResponseEntity.of(service.updateStudentCourses(studentCourse, id));
    }

    @DeleteMapping(value = "/studentcourses/{id}")
    public ResponseEntity<StudentCourses> deleteStudentCourses(@PathVariable Integer id) {
        if (service.deleteStudentCourses(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
