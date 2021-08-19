package com.company.backendcourses.service;

import com.company.backendcourses.dto.UserDto;
import com.company.backendcourses.persistence.entity.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    public UserDto validateCredentials(User userToCheck) {

        User user = userService.getUserByEmail(userToCheck.getEmail());

        String passwordHashed = user.getPassword();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        boolean isValidPassword = argon2.verify(passwordHashed, userToCheck.getPassword());

        if (isValidPassword){
            return modelMapper.map(user, UserDto.class);
        }
        return null;
    }
}
