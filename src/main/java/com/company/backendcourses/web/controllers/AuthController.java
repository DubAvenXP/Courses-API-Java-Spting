package com.company.backendcourses.web.controllers;

import com.company.backendcourses.dto.AuthDto;
import com.company.backendcourses.helpers.JWTUtil;
import com.company.backendcourses.persistence.entity.User;
import com.company.backendcourses.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping(value = "/login")
    public ResponseEntity<?> auth(@RequestBody User user) {
        AuthDto authDto = new AuthDto();
        authDto.setUser(authService.validateCredentials(user));
        if (authDto.getUser() == null) {
            return ResponseEntity.badRequest().body("wrong username or password");
        }
        authDto.setToken(jwtUtil.create(String.valueOf(authDto.getUser().getIdUser()), authDto.getUser().getEmail()));
        return ResponseEntity.ok().body(authDto);
    }

}
