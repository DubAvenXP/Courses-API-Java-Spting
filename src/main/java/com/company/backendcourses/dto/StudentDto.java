package com.company.backendcourses.dto;

import com.company.backendcourses.persistence.entity.StudentCourses;
import lombok.Data;

import java.util.List;

@Data
public class StudentDto {

    private String idStudent;
    private String username;
    private String name;
    private String lastName;
}
