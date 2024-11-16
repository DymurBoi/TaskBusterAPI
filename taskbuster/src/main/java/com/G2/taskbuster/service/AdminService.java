package com.G2.taskbuster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import com.G2.taskbuster.entity.AdminEntity;
import com.G2.taskbuster.repository.AdminRepository;
import com.G2.taskbuster.repository.UserRepository;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository, UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }
    
    public AdminEntity createAdmin(AdminEntity admin) {
        // No password encoding for simplicity
        admin.setCreatedAt(LocalDateTime.now());
        admin.setUpdatedAt(LocalDateTime.now());
        return adminRepository.save(admin);
    }

    public List<AdminEntity> getAllAdmins() {
        return adminRepository.findAll();
    }
    
    public AdminEntity getAdminByEmail(String email) {
        return adminRepository.findByEmail(email); // Ensure this returns null if not found
    }
    
    public AdminEntity loginAdmin(String email, String password) {
        AdminEntity admin = adminRepository.findByEmail(email);
        if (admin != null && admin.getPassword().equals(password)) {
            return admin;
        }
        throw new RuntimeException("Invalid email or password");
    }
    

}
