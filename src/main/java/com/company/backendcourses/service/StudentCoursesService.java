package com.company.backendcourses.service;

import com.company.backendcourses.persistence.crud.StudentCoursesCrudRepository;
import com.company.backendcourses.persistence.entity.StudentCourses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class StudentCoursesService {

    @Autowired
    private StudentCoursesCrudRepository crud;

    public List<StudentCourses> getStudentCourses(String id) {
        return (List<StudentCourses>) crud.findByIdStudent(id);
    }

    public List<StudentCourses> getStudentsByCourse(Integer id) {
        return (List<StudentCourses>) crud.findByIdCourse(id);
    }

    public List<StudentCourses> getAll() {
        return (List<StudentCourses>) crud.findByStatus("1");
    }

    public StudentCourses createStudentCourses(StudentCourses StudentCourses)  {
        return crud.save(StudentCourses);
    }

    public Optional<StudentCourses> updateStudentCourses(StudentCourses studentCourses, Integer id) {
        return crud.findById(id).map(studentCoursesToUpdate -> {
            studentCoursesToUpdate.setIdStudent(studentCourses.getIdStudent());
            studentCoursesToUpdate.setIdCourse(studentCourses.getIdCourse());
            studentCoursesToUpdate.setFinalGrade(studentCourses.getFinalGrade());
            return crud.save(studentCoursesToUpdate);
        });
    }

    public boolean deleteStudentCourses(@PathVariable Integer id) {
        return crud.findById(id).map(studentCoursesToUpdate -> {
            studentCoursesToUpdate.setStatus("0");
            crud.save(studentCoursesToUpdate);
            return true;
        }).orElse(false);
    }
}
