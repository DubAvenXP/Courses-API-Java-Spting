package com.company.backendcourses.persistence.crud;

import com.company.backendcourses.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserCrudRepository extends CrudRepository<User, Integer> {
    List<User> findByStatus(String status);
    Optional<User> findByStatusAndAndEmail(String status, String email);
}
