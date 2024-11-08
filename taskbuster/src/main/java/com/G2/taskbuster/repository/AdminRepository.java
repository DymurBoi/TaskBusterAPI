package com.G2.taskbuster.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.G2.taskbuster.entity.AdminEntity;

public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {
	
    public AdminEntity findByEmail(String email);

    public AdminEntity findById(int adminId);
    
    public Optional<AdminEntity> findByEmailAndPassword(String email, String password);
}