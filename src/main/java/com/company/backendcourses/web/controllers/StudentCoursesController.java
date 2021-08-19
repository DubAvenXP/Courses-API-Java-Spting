package com.company.backendcourses.web.controllers;

import com.company.backendcourses.dto.CoursesByStudentDto;
import com.company.backendcourses.dto.StudentsByCourseDto;
import com.company.backendcourses.persistence.entity.StudentCourses;
import com.company.backendcourses.service.StudentCoursesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
//@RequestMapping(value = "/api")
public class StudentCoursesController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StudentCoursesService service;

    @GetMapping(value = "/studentcourses/{id}")
    public ResponseEntity<List<CoursesByStudentDto>> getCoursesByStudent(@PathVariable String id) {
        List<CoursesByStudentDto> response = service.getCoursesByStudent(id).stream()
                .map(studentcourses -> modelMapper.map(studentcourses, CoursesByStudentDto.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/studentsbycourse/{id}")
    public ResponseEntity<List<StudentsByCourseDto>> getStudentsByCourse(@PathVariable Integer id) {
        List<StudentsByCourseDto> response = service.getStudentsByCourse(id).stream()
                .map(studentcourses -> modelMapper.map(studentcourses, StudentsByCourseDto.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/studentcourses")
    public ResponseEntity<List<CoursesByStudentDto>> getAll() {
        List<CoursesByStudentDto> response = service.getAll().stream()
                .map(studentcourses -> modelMapper.map(studentcourses, CoursesByStudentDto.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
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
