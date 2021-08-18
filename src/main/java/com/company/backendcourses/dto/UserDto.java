package com.company.backendcourses.dto;

import com.company.backendcourses.persistence.entity.Role;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserDto {
    private Integer idUser;
    private String username;
    private String name;
    private String lastName;
    private String email;
    private String profilePhoto;
    private Role role;
}
