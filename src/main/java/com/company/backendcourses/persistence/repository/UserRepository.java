package com.company.backendcourses.persistence.repository;

import com.company.backendcourses.persistence.crud.UserCrudRepository;
import com.company.backendcourses.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public class UserRepository {

    @Autowired
    private UserCrudRepository userCrudRepository;

    public Optional<User> getUser(Integer id) {
        return userCrudRepository.findById(id);
    }

    public List<User> getUsers() {
        return (List<User>) userCrudRepository.findByStatus("1");
    }

    public void createUser(User user) {
        userCrudRepository.save(user);
    }
    
    public Optional<User> updateUser(User user, Integer id) {
        return userCrudRepository.findById(id).map(userToUpdate -> {
            userToUpdate.setName(user.getName());
            userToUpdate.setLastName(user.getLastName());
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setProfilePhoto(user.getProfilePhoto());
            userToUpdate.setIdRole(user.getIdRole());
            return userCrudRepository.save(userToUpdate);
        });
    }

    public void deleteUser(@PathVariable Integer id) {
        userCrudRepository.findById(id).map(userToUpdate -> {
            userToUpdate.setStatus("0");
            return userCrudRepository.save(userToUpdate);
        });
    }
}