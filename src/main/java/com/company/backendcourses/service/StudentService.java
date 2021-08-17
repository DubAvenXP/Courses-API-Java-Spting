package com.company.backendcourses.service;

import com.company.backendcourses.helpers.CarnetGenerator;
import com.company.backendcourses.persistence.crud.StudentCrudRepository;
import com.company.backendcourses.persistence.entity.Course;
import com.company.backendcourses.persistence.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentCrudRepository studentCrudRepository;

    public Optional<Student> getStudent(String id) {
        return studentCrudRepository.findById(id);
    }

    public List<Student> getStudents() {
        return (List<Student>) studentCrudRepository.findByStatus("1");
    }

    public Student createStudent(Student student)  {
        CarnetGenerator carnetGenerator = new CarnetGenerator();
        student.setIdStudent(carnetGenerator.generate((int) studentCrudRepository.count()));
        return studentCrudRepository.save(student);
    }

    public Optional<Student> updateStudent(Student student, String id) {
        return studentCrudRepository.findById(id).map(studentToUpdate -> {
            studentToUpdate.setName(student.getName());
            studentToUpdate.setLastName(student.getLastName());
            studentToUpdate.setUsername(student.getUsername());
            return studentCrudRepository.save(studentToUpdate);
        });
    }

    public boolean deleteCourse(@PathVariable String id) {
        return studentCrudRepository.findById(id).map(studentToUpdate -> {
            studentToUpdate.setStatus("0");
            studentCrudRepository.save(studentToUpdate);
            return true;
        }).orElse(false);
    }
}
