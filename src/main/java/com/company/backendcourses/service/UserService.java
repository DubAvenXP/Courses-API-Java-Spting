package com.company.backendcourses.service;

import com.company.backendcourses.persistence.crud.UserCrudRepository;
import com.company.backendcourses.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserCrudRepository userCrudRepository;

    public User getUser(Integer id) {
        Optional<User> result = userCrudRepository.findById(id);

        if (result.isPresent()) {
            return result.get();
        } else {
            throw new IllegalStateException("User not found");
        }
    }

    public List<User> getUsers() {
        return (List<User>) userCrudRepository.findByStatus("1");
    }

    public User createUser(User user)  {
        return userCrudRepository.save(user);
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

    public boolean deleteUser(@PathVariable Integer id) {
        return userCrudRepository.findById(id).map(userToUpdate -> {
            userToUpdate.setStatus("0");
            userCrudRepository.save(userToUpdate);
            return true;
        }).orElse(false);
    }
}