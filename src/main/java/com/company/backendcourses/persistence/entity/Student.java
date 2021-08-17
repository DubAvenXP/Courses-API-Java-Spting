package com.company.backendcourses.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @Getter @Setter @Column(name = "id_student")
    private String idStudent;

    @Getter @Setter
    private String username;

    @Getter @Setter
    private String name;

    @Getter @Setter @Column(name = "last_name")
    private String lastName;

    @Getter @Setter
    private String status;

    @Getter @Setter
    private String password;

    /*@OneToMany(mappedBy = "student")
    private List<StudentCourse> studentCourseList;*/

}
