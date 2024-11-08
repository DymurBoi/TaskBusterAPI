package com.G2.taskbuster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.G2.taskbuster.entity.TaskEntity;
import com.G2.taskbuster.service.TaskService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Get all tasks for a specific todo list
    @GetMapping("/todo-list/{todoListId}")
    public List<TaskEntity> getTasksByTodoListId(@PathVariable int todoListId) {
        return taskService.findByTodoListId(todoListId);
    }

    // Create a new task
    @PostMapping	
    public TaskEntity createTask(@RequestBody TaskEntity task) {
        return taskService.save(task);
    }

    // Delete a task by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
 // Add this method to your TaskController class
    @PutMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable int id, @RequestBody TaskEntity updatedTask) {
        // First, find the existing task
        TaskEntity existingTask = taskService.findById(id);
        if (existingTask == null) {
            return ResponseEntity.notFound().build();
        }

        // Update the task properties
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setStatus(updatedTask.getStatus());
        existingTask.setDueDate(updatedTask.getDueDate());
        existingTask.setUpdatedAt(LocalDateTime.now()); // Update the updatedAt timestamp

        // Save the updated task
        TaskEntity savedTask = taskService.save(existingTask);
        return ResponseEntity.ok(savedTask);
    }


    // Optionally, you can add methods for updating a task, getting by ID, etc.
}
