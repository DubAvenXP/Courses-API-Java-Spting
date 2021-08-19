package com.company.backendcourses.web.controllers;

import com.company.backendcourses.dto.CourseDto;
import com.company.backendcourses.dto.StudentDto;
import com.company.backendcourses.persistence.entity.Course;
import com.company.backendcourses.persistence.entity.Student;
import com.company.backendcourses.service.StudentService;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
//@RequestMapping(value = "/api")
public class StudentController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/student/{id}")
    public ResponseEntity<StudentDto> getCourse(@PathVariable String id) {
        Student student = studentService.getStudent(id);
        StudentDto response = modelMapper.map(student, StudentDto.class);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/student")
    public ResponseEntity<List<StudentDto>> getCourse() {
        List<StudentDto> students = studentService.getStudents().stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping(value = "/student")
    public ResponseEntity<Student> createCourse(@RequestBody Student student) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        student.setPassword(argon2.hash(7, 1024, 2, student.getPassword()));
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
