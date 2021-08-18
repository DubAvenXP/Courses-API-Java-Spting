package com.company.backendcourses.web.controllers;

import com.company.backendcourses.dto.UserDto;
import com.company.backendcourses.persistence.entity.User;
import com.company.backendcourses.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private ModelMapper modelmapper;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer id) {
        User user = userService.getUser(id);
        UserDto userResponse = modelmapper.map(user, UserDto.class);
        return ResponseEntity.ok().body(userResponse);
    }

    @GetMapping(value = "/user")
    public ResponseEntity<List<UserDto>> getUsers() {

        List<UserDto> users = userService.getUsers().stream()
                .map(user -> modelmapper.map(user, UserDto.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping(value = "/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping(value = "/user/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Integer id) {
        return ResponseEntity.of(userService.updateUser(user, id));
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Integer id) {
        if (userService.deleteUser(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
