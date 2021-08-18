package com.company.backendcourses.dto;

import lombok.Data;

@Data
public class StudentsByCourseDto {
    private Integer id;
    private Integer finalGrade;
    private String status;
    private StudentDto student;
}
