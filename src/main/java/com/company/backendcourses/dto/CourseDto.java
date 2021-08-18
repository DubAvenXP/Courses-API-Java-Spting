package com.company.backendcourses.dto;

import lombok.Data;

@Data
public class CourseDto {

    private int idCourse;
    private String name;
    private String section;
    private String startDate;
    private String finalDate;
    private String startTime;
    private String endTime;
    private UserDto2 user;
}
