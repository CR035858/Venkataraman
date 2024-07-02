package com.komal.students.repository;

import com.komal.students.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    public User findByUsername(String username);
}
