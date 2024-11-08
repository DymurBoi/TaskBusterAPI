package com.G2.taskbuster.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.G2.taskbuster.entity.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
    // Method to find tasks by todo list ID
    List<TaskEntity> findByTodoList_TodoListId(int todoListId);
}
