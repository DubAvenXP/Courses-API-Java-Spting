package com.company.backendcourses.web.controllers;

import com.company.backendcourses.dto.CourseDto;
import com.company.backendcourses.dto.UserDto;
import com.company.backendcourses.persistence.entity.Course;
import com.company.backendcourses.persistence.entity.User;
import com.company.backendcourses.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class CourseController {

    @Autowired
    private ModelMapper modelmapper;

    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/course/{id}")
    public ResponseEntity<CourseDto> getCourse(@PathVariable Integer id) {
        Course course = courseService.getCourse(id);
        CourseDto courseResponse = modelmapper.map(course, CourseDto.class);
        return ResponseEntity.ok().body(courseResponse);
    }

    @GetMapping(value = "/course")
    public ResponseEntity<List<CourseDto>> getCourse() {
        List<CourseDto> courses = courseService.getCourses().stream()
                .map(course -> modelmapper.map(course, CourseDto.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(courses, HttpStatus.OK);
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
