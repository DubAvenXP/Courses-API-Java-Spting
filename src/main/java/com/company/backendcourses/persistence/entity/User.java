package com.company.backendcourses.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id_user")
    private Integer idUser;

    @Getter @Setter
    private String username;

    @Getter @Setter
    private String name;

    @Getter @Setter @Column(name = "last_name")
    private String lastName;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String password;

    @Getter @Setter
    @Column(name = "profile_photo")
    private String profilePhoto;

    @Getter @Setter
    private String status;

    @Getter @Setter
    @Column(name = "id_role")
    private Integer idRole;

    @ManyToOne
    @Getter @Setter @JoinColumn(name = "id_role", insertable = false, updatable = false)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Course> courses;

}
