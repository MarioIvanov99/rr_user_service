package com.mario.userservice.data.repository;

import com.mario.userservice.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
