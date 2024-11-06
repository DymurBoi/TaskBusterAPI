package com.G2.taskbuster.repository;

import com.G2.taskbuster.entity.UserEntity;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
  public UserEntity findById(int id);
  Optional<UserEntity> findByEmailAndPassword(String email, String password);
}
