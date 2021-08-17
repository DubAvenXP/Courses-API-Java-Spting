package com.company.backendcourses.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "student_courses")
public class StudentCourses {

    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter @Setter @Column(name = "id_student")
    private String idStudent;

    @Getter @Setter @Column(name = "id_course")
    private Integer idCourse;

    @Getter @Setter @Column(name = "final_grade")
    private Integer finalGrade;

    @Getter @Setter
    private String status;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "id_student", insertable = false, updatable = false)
    private Student student;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "id_course", insertable = false, updatable = false)
    private Course course;

}
