package com.company.backendcourses.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@ToString
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id_role")
    private Integer idRole;

    @Getter @Setter
    private String name;

    @OneToMany(mappedBy = "role")
    private List<User> users;
}
