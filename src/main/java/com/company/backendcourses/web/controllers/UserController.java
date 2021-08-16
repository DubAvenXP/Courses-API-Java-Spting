package com.company.backendcourses.web.controllers;

import com.company.backendcourses.persistence.crud.UserCrudRepository;
import com.company.backendcourses.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private UserCrudRepository crud;

    @GetMapping(value = "/user/{id}")
    public Optional<User> getUsuario(@PathVariable Integer id) {
        return crud.findById(id);
    }

    @GetMapping(value = "/user")
    public List<User> getUsers() {
        return (List<User>) crud.findAll();
    }

    @PostMapping(value = "/user")
    public void createUser(@RequestBody User user) {
        crud.save(user);
    }

    @PutMapping(value = "/user/{id}")
    public Optional<User> updateUser(@RequestBody User user, @PathVariable Integer id) {

        return crud.findById(id).map(userToUpdate -> {
            userToUpdate.setName(user.getName());
            userToUpdate.setLastName(user.getLastName());
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setProfilePhoto(user.getProfilePhoto());
            userToUpdate.setIdRole(user.getIdRole());
            return crud.save(userToUpdate);
        });
    }

    @DeleteMapping(value = "/user/{id}")
    public void deleteUser(@PathVariable Integer id) {
        crud.findById(id).map(userToUpdate -> {
            userToUpdate.setStatus("0");
            return crud.save(userToUpdate);
        });
    }

}
