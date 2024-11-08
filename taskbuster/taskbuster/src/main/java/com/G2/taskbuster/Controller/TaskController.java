package com.G2.taskbuster.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.G2.taskbuster.Entity.Task;
import com.G2.taskbuster.Service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from your frontend app
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/add")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        System.out.println("Adding task: " + task); // Log the incoming task
        Task createdTask = taskService.addTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping("/view")
    public ResponseEntity<List<Task>> getTasks() {
        List<Task> tasks = taskService.getAllTasks();
        System.out.println("Retrieved tasks: " + tasks); // Log the retrieved tasks
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        Task task = taskService.findById(id);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }

        // Update fields
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        
        // Save updated task
        Task updatedTask = taskService.save(task);
        return ResponseEntity.ok(updatedTask);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
