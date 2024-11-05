package com.G2.taskbuster.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.G2.taskbuster.entity.AdminEntity;
import com.G2.taskbuster.entity.UserEntity;
import com.G2.taskbuster.repository.AdminRepository;
import com.G2.taskbuster.repository.UserRepository;

@Service
public class UserService {

    UserRepository userRepo;
    AdminRepository adminRepo;

    public UserService() {
        super();
    }

    @Autowired
    public UserService(UserRepository userRepo, AdminRepository adminRepo) {
        this.userRepo = userRepo;
        this.adminRepo = adminRepo;
    }


    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }


    public UserEntity postUser(UserEntity user, int adminId) {
    	user.setCreatedAt(LocalDateTime.now());
        try {
            AdminEntity admin = adminRepo.findById(adminId);
            if (admin != null) {
                user.setAdmin(admin);
                return userRepo.save(user);
            } else {
                throw new RuntimeException("Admin with ID " +adminId + " not found.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while saving the user: " + e.getMessage(), e);
        }
    }
    
    
    @SuppressWarnings("finally")
    public UserEntity putUserRecord(int id, UserEntity newUserRecord) {
        UserEntity user = new UserEntity();

        try {
            user = userRepo.findById(id);
            user.setUpdatedAt(LocalDateTime.now());
            user.setName(newUserRecord.getName());
            user.setEmail(newUserRecord.getEmail());
            user.setPassword(newUserRecord.getPassword());

        }catch(NoSuchElementException nex){
            throw new NameNotFoundException("User " + id + " not found.");
        }finally {
            return userRepo.save(user);

        }
    }


    public String deleteUser(int userId) {
        String msg = "";
        if(userRepo.findById(userId)!=null) {
            userRepo.deleteById(userId);
            msg = "User Successfully deleted";
        }else {
            msg = "Not Found";
        }

        return msg;
    }
}