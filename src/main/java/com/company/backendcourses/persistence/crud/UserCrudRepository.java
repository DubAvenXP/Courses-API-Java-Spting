package com.company.backendcourses.persistence.crud;

import com.company.backendcourses.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserCrudRepository extends CrudRepository<User, Integer> {
    List<User> findByStatus(String status);
}
