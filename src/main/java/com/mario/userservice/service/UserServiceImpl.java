package com.mario.userservice.service;

import com.mario.userservice.data.entity.User;
import com.mario.userservice.data.repository.UserRepository;
import com.mario.userservice.dto.UpdateUserRequest;
import com.mario.userservice.dto.UserAdminResponse;
import com.mario.userservice.dto.UserResponse;
import com.mario.userservice.exception.EmailAlreadyExistsException;
import com.mario.userservice.exception.UserNotFoundException;
import com.mario.userservice.exception.UsernameAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse findUserById(Long id){
        return userRepository.findUserById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public UserAdminResponse findUserAdminById(Long id){
        return userRepository.findUserAdminById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public List<UserAdminResponse> findAllUsers(){
        return userRepository.findAllUsers();
    }

    @Override
    public UserResponse getCurrentUser(){
        return null;
    }

    @Override
    public UserResponse updateUser(Long id, UpdateUserRequest dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (dto.getUsername() != null && !dto.getUsername().equals(user.getUsername()) &&
                userRepository.existsByUsername(dto.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        if (dto.getEmail() != null && !dto.getEmail().equals(user.getEmail()) &&
                userRepository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        if (dto.getUsername() != null) user.setUsername(dto.getUsername());
        if (dto.getEmail() != null) user.setEmail(dto.getEmail());

        userRepository.save(user);

        return new UserResponse(user.getUsername(), user.getEmail());
    }

    @Override
    public void deleteUser(Long id){

    }
}
