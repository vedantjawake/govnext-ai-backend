package com.govnext.backend.service;

import com.govnext.backend.dto.UserDto;
import com.govnext.backend.dto.UserProfileDto;
import com.govnext.backend.entity.User;
import com.govnext.backend.exception.ResourceNotFoundException;
import com.govnext.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public UserProfileDto getUserProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));

        return new UserProfileDto(user.getName(), user.getEmail(), Collections.emptyList(), Collections.emptyList());
    }

    public User updateUser(Long id, UserDto userDto) {
        User user = getUserById(id);
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }
}