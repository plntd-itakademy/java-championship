package com.example.championship.controllers;

import com.example.championship.models.User;
import com.example.championship.repositories.UserRepository;
import com.example.championship.requests.LoginRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/")
    public List<User> all() {
        if (userRepository.count() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No users found");
        }
        return userRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public User getOne(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @PostMapping(value = "/login")
    public User getByEmailAndPassword(@Valid @RequestBody LoginRequest loginRequest,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        } else {
            return userRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        }
    }

    @PostMapping(value = "/")
    public ResponseEntity<User> save(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        } else {
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @Valid @RequestBody User newUser,
            BindingResult bindingResult) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        } else {
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setEmail(newUser.getEmail());
            user.setPassword(newUser.getPassword());
            user.setCreationDate(newUser.getCreationDate());
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<User> delete(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        userRepository.delete(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
