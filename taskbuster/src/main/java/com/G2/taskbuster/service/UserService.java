package com.G2.taskbuster.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.G2.taskbuster.entity.UserEntity;
import com.G2.taskbuster.repository.UserRepository;
import javax.naming.NameNotFoundException;
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public UserService() {
        super();
    }

    // Create a new user
    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    // Read all users
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }


    // Update user details
    @SuppressWarnings("finally")
    public UserEntity updateUserDetails(int id, UserEntity newUserDetails) {
        UserEntity user = new UserEntity();

        try{
            user = userRepository.findById(id);

            user.setName(newUserDetails.getName());
            user.setEmail(newUserDetails.getEmail());
            user.setPassword(newUserDetails.getPassword());
            user.setUpdatedAt(newUserDetails.getUpdatedAt());
        }
        catch(NoSuchElementException nex){
            throw new NameNotFoundException("Teacher "+ id +" not found.");
        } finally{
            return userRepository.save(user);
        }
    }

    // Delete a user
    public String deleteUser(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User record successfully deleted.";
        } else {
            return "User with ID " + id + " not found!";
        }
    }

    // Read to-do list by ID
    public UserEntity getUserById(int id) {
        return userRepository.findById(id);
    }

    //LOGIN
    public UserEntity loginUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
    }

}

