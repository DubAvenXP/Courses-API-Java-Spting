package com.company.backendcourses.persistence.crud;

import com.company.backendcourses.persistence.entity.StudentCourses;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentCoursesCrudRepository extends CrudRepository<StudentCourses, Integer> {
    List<StudentCourses> findByIdStudent(String id);
    List<StudentCourses> findByIdCourse(Integer id);
    List<StudentCourses> findByStatus(String status);

}
