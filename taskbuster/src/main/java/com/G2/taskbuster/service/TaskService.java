package com.G2.taskbuster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.G2.taskbuster.entity.TaskEntity;
import com.G2.taskbuster.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // Retrieve all tasks for a specific todo list
    public List<TaskEntity> findByTodoListId(int todoListId) {
        return taskRepository.findByTodoList_TodoListId(todoListId);
    }

    // Save a new task
    public TaskEntity save(TaskEntity task) {
        return taskRepository.save(task);
    }

    // Delete a task by ID
    public void deleteById(int id) {
        taskRepository.deleteById(id);
    }
    
 // Add this method to your TaskService class
    public TaskEntity findById(int id) {
        return taskRepository.findById(id).orElse(null);
    }

    // Optionally, you can add methods for finding a task by ID, updating, etc.
}
