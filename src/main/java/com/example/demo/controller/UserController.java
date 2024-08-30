package com.example.demo.controller;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.dto.requests.UserRequest;
import com.example.demo.model.dto.responses.UserResponse;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/")
    public List<UserResponse> getAllUsers() {
        return userMapper.mapListUserToListUserResponse(userService.getAllUsers());
    }

    @GetMapping("/{uuid}")
    public UserResponse getUserById(@PathVariable UUID uuid) {
        return userMapper.mapEntityToUserResponse(userService.getUserByUUID(uuid));
    }

    @PostMapping
    public UserResponse createUSer(@RequestBody UserRequest userRequest) {
        return userMapper.mapEntityToUserResponse(userService.createUser(userRequest));
    }

    @PutMapping
    public UserResponse updateUser(@RequestBody UserRequest userRequest) {
        return userMapper.mapEntityToUserResponse(userService.updateUser(userRequest));
    }

    @DeleteMapping("/{uuid}")
    public void deleteUserById(@PathVariable UUID uuid) {
        userService.deleteUser(uuid);
    }
}
