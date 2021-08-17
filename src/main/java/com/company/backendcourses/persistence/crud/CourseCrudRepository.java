package com.company.backendcourses.persistence.crud;

import com.company.backendcourses.persistence.entity.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseCrudRepository extends CrudRepository<Course, Integer> {
     List<Course> findByStatus(String status);
}
