package com.company.backendcourses.dto;

import lombok.Data;

@Data
public class AuthDto {

    private String token;
    private UserDto user;

}
