package com.example.demo.controller;

import com.example.demo.dot.UserDto;
import com.example.demo.dot.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        User user = UserMapper.toEntity(userDto);
        // ici tu peux encoder le mot de passe si nécessaire
        return UserMapper.toDto(userService.save(user));
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.findById(id)
                .map(UserMapper::toDto)
                .orElse(null);
    }
}
