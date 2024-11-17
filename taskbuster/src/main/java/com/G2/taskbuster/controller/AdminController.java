package com.G2.taskbuster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.G2.taskbuster.entity.AdminEntity;
import com.G2.taskbuster.service.AdminService;

import java.util.List;

@RestController
@RequestMapping("/api/taskbuster/admin")
public class AdminController {

    private final AdminService adminServe;

    @Autowired
    public AdminController(AdminService adminServe) {
        this.adminServe = adminServe;
    }

    @PostMapping("/createAdmin")
    public AdminEntity createAdmin(@RequestBody AdminEntity admin) {
        return adminServe.createAdmin(admin);
    }
    
    @GetMapping("/getAllAdmins")
    public List<AdminEntity> getAllAdmins() {
        return adminServe.getAllAdmins();
    }   

    @CrossOrigin(origins = "/**") 
    @PostMapping("/adminLogin")
    public AdminEntity adminLogin(@RequestBody AdminEntity adminRequest) {
        try {
            return adminServe.loginAdmin(adminRequest.getEmail(), adminRequest.getPassword());
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }


}
