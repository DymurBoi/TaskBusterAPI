package com.G2.taskbuster.repository;

import com.G2.taskbuster.entity.ToDoListEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoListRepository extends JpaRepository<ToDoListEntity, Integer> {
    public ToDoListEntity findById(int toDoListID);
     List<ToDoListEntity> findByUserUserId(int userId);
}