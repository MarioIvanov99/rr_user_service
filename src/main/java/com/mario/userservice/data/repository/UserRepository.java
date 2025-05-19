package com.mario.userservice.data.repository;

import com.mario.userservice.data.entity.User;
import com.mario.userservice.dto.UserAdminResponse;
import com.mario.userservice.dto.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Query("SELECT new com.mario.userservice.dto.UserAdminResponse(u.id, u.username, u.email) FROM User u WHERE u.id = :id")
    Optional<UserAdminResponse> findUserById(@Param("id") Long id);

    @Query("SELECT new com.mario.userservice.dto.UserResponse(u.username, u.email) FROM User u WHERE u.id = :id")
    Optional<UserResponse> findUserResponseById(@Param("id") Long id);

    @Query("SELECT new com.mario.userservice.dto.UserAdminResponse(u.id, u.username, u.email) FROM User u")
    List<UserAdminResponse> findAllUsers();
}
