package com.company.backendcourses.dto;

import com.company.backendcourses.persistence.entity.Role;
import lombok.Data;

@Data
public class UserDto2 {
    private Integer idUser;
    private String username;
    private String name;
    private String lastName;
    private String profilePhoto;
}
