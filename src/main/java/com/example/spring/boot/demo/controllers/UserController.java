package com.example.spring.boot.demo.controllers;

import com.example.spring.boot.demo.entities.UserEntity;
import com.example.spring.boot.demo.exceptions.NotFoundException;
import com.example.spring.boot.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity user) {
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow( () -> new NotFoundException("Users is not found with this id: "+id));
    }

    @PutMapping("/{id}")
    public UserEntity updateUser(@PathVariable Long id, @RequestBody UserEntity user) {
        UserEntity oldUser = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User is not found with this id: "+id));
        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        return userRepository.save(oldUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        UserEntity userData = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Users is not found with this id: "+id));
        userRepository.delete(userData);
        return ResponseEntity.ok().build();
    }

}
