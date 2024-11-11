package com.G2.taskbuster.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.G2.taskbuster.entity.UserEntity;
import com.G2.taskbuster.service.UserService;


@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins= "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the User API!";
    }

    // Create a new user
    @PostMapping("/create")
    public UserEntity createUser(@RequestBody UserEntity user) {
        return userService.createUser(user);
    }

    @PostMapping("/postUser")
    public UserEntity postUser(@RequestBody UserEntity user) {
        int adminId = user.getAdmin().getAdminId();
        return userService.postUser(user,adminId);
    }

    // Read all users
    @GetMapping("/all")
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    //Read user by ID
    @GetMapping("/read/{id}")
    public UserEntity getUserById(@PathVariable int id) {
         System.out.println("Attempting to fetch user with ID: " + id);
    return userService.getUserById(id);
    }

    // Update user details
    @PutMapping("/update")
    public UserEntity updateUserDetails(@RequestParam int id, @RequestBody UserEntity newUserDetails) {
        return userService.updateUserDetails(id, newUserDetails);
    }

    @PutMapping("/putUser/{userId}")
    public UserEntity putUser(@PathVariable int userId, @RequestBody UserEntity newUserRecord) {
        return userService.putUserRecord(userId, newUserRecord);
    }

    // Delete user by ID
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }


    //LOGIN FUNCTIONALITY
    @PostMapping("/login")
    public UserEntity login(@RequestParam String email, @RequestParam String password) {
    try {
        return userService.loginUser(email, password);
    } catch (RuntimeException e) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}
}
