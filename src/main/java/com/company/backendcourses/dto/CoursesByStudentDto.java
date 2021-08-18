package com.company.backendcourses.dto;

import com.company.backendcourses.persistence.entity.Course;
import com.company.backendcourses.persistence.entity.Student;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
public class CoursesByStudentDto {

    private Integer id;
    private Integer finalGrade;
    private String status;
    private CourseDto course;

}
