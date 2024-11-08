package com.G2.taskbuster.Service;

import com.G2.taskbuster.Entity.Task;
import com.G2.taskbuster.Repository.TaskRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setStatus(updatedTask.getStatus());
            task.setDueDate(updatedTask.getDueDate());
            return taskRepository.save(task);
        }).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    // Complete implementation for findById method
    public Task findById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    // Complete implementation for save method
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public void deleteById(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException("Task not found with id " + id);
        }
        taskRepository.deleteById(id);
    }

    // New method to update task by title
    public Task updateTaskByTitle(String title, Task updatedTask) {
        Task task = taskRepository.findByTitle(title)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with title " + title));

        // Update fields
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setStatus(updatedTask.getStatus());
        task.setDueDate(updatedTask.getDueDate());

        return taskRepository.save(task);
    }
}
