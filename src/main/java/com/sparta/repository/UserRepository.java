package com.sparta.repository;


import com.sparta.models.Users1;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users1, Long> {
    Optional<Users1> findByUsername(String username);
}