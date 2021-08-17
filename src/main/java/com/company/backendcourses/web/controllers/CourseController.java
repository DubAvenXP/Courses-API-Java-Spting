package com.company.backendcourses.web.controllers;

import com.company.backendcourses.persistence.entity.Course;
import com.company.backendcourses.persistence.entity.User;
import com.company.backendcourses.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/course/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Integer id) {
        return ResponseEntity.of(courseService.getCourse(id));
    }

    @GetMapping(value = "/course")
    public ResponseEntity<List<Course>> getCourse() {
        return new ResponseEntity<>(courseService.getCourses(), HttpStatus.OK);
    }

    @PostMapping(value = "/course")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        return new ResponseEntity<>(courseService.createCourse(course), HttpStatus.CREATED);
    }

    @PutMapping(value = "/course/{id}")
    public ResponseEntity<Course> updateCourse(@RequestBody Course course, @PathVariable Integer id) {
        return ResponseEntity.of(courseService.updateCourse(course, id));
    }

    @DeleteMapping(value = "/course/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable Integer id) {
        if (courseService.deleteCourse(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
