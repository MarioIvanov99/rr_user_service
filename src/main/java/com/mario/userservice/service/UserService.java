package com.mario.userservice.service;

import com.mario.userservice.dto.UpdateUserRequest;
import com.mario.userservice.dto.UserAdminResponse;
import com.mario.userservice.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse findUserById(Long id);
    UserAdminResponse findUserAdminById(Long id);
    List<UserAdminResponse> findAllUsers();
    UserResponse getCurrentUser();
    UserResponse updateUser(Long id, UpdateUserRequest updateUserRequest);
    void deleteUser(Long id);
}
