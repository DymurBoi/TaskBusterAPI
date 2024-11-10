package com.G2.taskbuster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.G2.taskbuster.entity.CommentEntity;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer>{
    public CommentEntity findById(int commentId);
}
