package com.G2.taskbuster.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.G2.taskbuster.entity.UserEntity;
import com.G2.taskbuster.service.UserService;

@RestController
@RequestMapping("/api/taskbuster")
public class UserController {

    private UserService userServe;

    @Autowired
    public UserController(UserService userServe) {
        this.userServe = userServe;
    }

    @GetMapping("/getAllUsers")
    public List<UserEntity> getAllUsers() {
        return userServe.getAllUsers();
    }

    @PostMapping("/postUser")
    public UserEntity postUser(@RequestBody UserEntity user) {
        int adminId = user.getAdmin().getAdminId();
        return userServe.postUser(user,adminId);
    }

    @PutMapping("/putUser/{userId}")
    public UserEntity putUser(@PathVariable int userId, @RequestBody UserEntity newUserRecord) {
        return userServe.putUserRecord(userId, newUserRecord);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable int userId) {
        return userServe.deleteUser(userId);
    }

}