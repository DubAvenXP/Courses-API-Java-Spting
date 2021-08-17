package com.company.backendcourses.persistence.crud;

import com.company.backendcourses.persistence.entity.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentCrudRepository extends CrudRepository<Student, String> {
    List<Student> findByStatus(String status);
}
