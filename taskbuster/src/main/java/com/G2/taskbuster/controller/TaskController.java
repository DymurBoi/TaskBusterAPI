package com.G2.taskbuster.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.G2.taskbuster.entity.TaskEntity;

import com.G2.taskbuster.service.TaskService;


@RestController
@RequestMapping(method=RequestMethod.GET,path="/api/taskbuster")
public class TaskController {
    private final TaskService tserv;
    @Autowired
    public TaskController(TaskService tserv){
        this.tserv = tserv;
    }

    @PostMapping("/postTask")
    public TaskEntity postTask(@RequestBody TaskEntity task){
        return tserv.postTask(task, task.getTag().getTagId(),task.getToDoList().getToDoListID());
    }
    @GetMapping("/getTask")
    public List<TaskEntity> getAllTasks(){
        return tserv.getAllTasks();
    }
    @PutMapping("/putTask")
    public TaskEntity putTask(@RequestParam int taskId, @RequestBody TaskEntity newTask){
        return tserv.putTask(taskId, newTask);
    }
    @DeleteMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable int id){
        return tserv.deleteTask(id);
    }
    @GetMapping("/getTasks")
     public List<TaskEntity> getTaskByToDoListID(@RequestParam int toDoListID) {
        return tserv.getTaskByToDoListID(toDoListID);
    }
    @GetMapping("getTask/{taskId}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable int taskId) {
        try {
            TaskEntity task = tserv.getTaskById(taskId);  // Calling service method to fetch the task by ID
            return new ResponseEntity<>(task, HttpStatus.OK);  // Return the task with HTTP 200 OK status
        } catch (RuntimeException e) {
            // Handle exception and return an error response
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);  // Return 404 if task not found
        }
    }

    @GetMapping("/TaskCount")
    public int getTaskCount() {
        return tserv.getTaskCount();
    }
}
