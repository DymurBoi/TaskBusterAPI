package com.G2.taskbuster.controller;


import com.G2.taskbuster.entity.ToDoListEntity;
import com.G2.taskbuster.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class ToDoListController {

    private final ToDoListService toDoListService;
    @Autowired
    public ToDoListController(ToDoListService toDoListService){
        this.toDoListService = toDoListService;
    }


    // Create a new to-do list
    @PostMapping("/createT")
    public ToDoListEntity createToDoList(@RequestBody ToDoListEntity toDoList) {
        return toDoListService.createToDoList(toDoList, toDoList.getUser().getUserId());
    }

    // Read all to-do lists
    @GetMapping("/allT")
    public List<ToDoListEntity> getAllToDoLists() {
        return toDoListService.getAllToDoLists();
    }

    // Read to-do list by ID
    // @GetMapping("/{id}")
    // public ToDoListEntity getToDoListById(@PathVariable int id) {
    //     return toDoListService.getToDoListById(id);
    // }

    // Update to-do list details
    @PutMapping("/updateT")
    public ToDoListEntity updateToDoList(@RequestParam int id, @RequestBody ToDoListEntity   newToDoListDetails) {
        return toDoListService.updateToDoList(id, newToDoListDetails);
    }

    // Delete to-do list by ID
    @DeleteMapping("/deleteT/{id}")
    public String deleteToDoList(@PathVariable int id) {
        return toDoListService.deleteToDoList(id);
    }

    //Get ToDoList by UserId
    @GetMapping("/todos")
    public List<ToDoListEntity> getToDosByUserId(@RequestParam int userId) {
    return toDoListService.getToDosByUserId(userId);
}
}
