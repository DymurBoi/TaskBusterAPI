package com.G2.taskbuster.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.G2.taskbuster.entity.AdminEntity;
import com.G2.taskbuster.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{

    public UserEntity findById(int userId);

}