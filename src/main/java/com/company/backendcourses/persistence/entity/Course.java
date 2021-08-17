package com.company.backendcourses.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id_course")
    private int idCourse;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String section;

    @Getter @Setter @Column(name = "start_date")
    private String startDate;

    @Getter @Setter @Column(name = "final_date")
    private String finalDate;

    @Getter @Setter @Column(name = "start_time")
    private String startTime;

    @Getter @Setter @Column(name = "end_time")
    private String endTime;

    @Getter @Setter
    private String status;

    @Getter @Setter @Column(name = "id_user")
    private Integer idUser;

    @ManyToOne
    @Getter @Setter @JoinColumn(name = "id_user", insertable = false, updatable = false)
    private User user;
}

