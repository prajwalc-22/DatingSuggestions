package com.datingsuggestion.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.datingsuggestion.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    
    @Query( value = "SELECT * FROM User LIMIT 2", nativeQuery = true)
    List<User> findAllTop2();
}

