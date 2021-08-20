package com.company.backendcourses.web.controllers;

import com.company.backendcourses.dto.UserDto;
import com.company.backendcourses.helpers.JWTUtil;
import com.company.backendcourses.persistence.entity.User;
import com.company.backendcourses.service.UserService;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private ModelMapper modelmapper;

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping(value = "/user/{id}")
    @ApiOperation("Search user by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "User not found")
    })
    public ResponseEntity<UserDto> getUser(
            @ApiParam(value = "The id of the user", required = true, example = "1")
            @PathVariable Integer id) {

        User user = userService.getUser(id);
        UserDto userResponse = modelmapper.map(user, UserDto.class);
        return ResponseEntity.ok().body(userResponse);
    }

    @GetMapping(value = "/user")
    @ApiOperation("Get all users")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<UserDto>> getUsers() {

        List<UserDto> users = userService.getUsers().stream()
                .map(user -> modelmapper.map(user, UserDto.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping(value = "/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        user.setPassword(argon2.hash(7, 1024, 2, user.getPassword()));
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping(value = "/user/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Integer id) {
        return ResponseEntity.of(userService.updateUser(user, id));
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Integer id, @RequestHeader(value = "Authorization") String token) {
        //TODO: proteger rutas
        /*if (jwtUtil.getKey(token) == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }*/
        if (userService.deleteUser(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
