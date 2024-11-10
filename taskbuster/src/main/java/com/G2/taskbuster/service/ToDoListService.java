package com.G2.taskbuster.service;

import com.G2.taskbuster.repository.UserRepository;
import com.G2.taskbuster.entity.ToDoListEntity;
import com.G2.taskbuster.entity.UserEntity;
import com.G2.taskbuster.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import javax.naming.NameNotFoundException;

@Service
public class ToDoListService {

    @Autowired
    ToDoListRepository toDoListRepository;
    UserRepository userRepository;

    public ToDoListService(){
        super();
    }
    @Autowired
    public ToDoListService(ToDoListRepository toDoListRepository,UserRepository userRepository) {
        this.toDoListRepository = toDoListRepository;
        this.userRepository = userRepository;
    }

    // Create a new to-do list
    public ToDoListEntity createToDoList(ToDoListEntity toDoList, int userId) {
        try {
            // Assuming you have a UserRepository to fetch users by their ID
            UserEntity user = userRepository.findById(userId);
            if (user != null) {
                toDoList.setUser(user);
                return toDoListRepository.save(toDoList);
            } else {
                throw new RuntimeException("User with ID " + userId + " not found.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while saving the ToDoList: " + e.getMessage(), e);
        }
    }
    

    // Read all to-do lists
    public List<ToDoListEntity> getAllToDoLists() {
        return toDoListRepository.findAll();
    }

    // // Read to-do list by ID
    // public ToDoListEntity getToDoListById(int id) {
    //     return toDoListRepository.findById(id).orElseThrow(() ->
    //         new NoSuchElementException("To-Do List with ID " + id + " not found."));
    // }

    // Update to-do list details
    @SuppressWarnings("finally")
    public ToDoListEntity updateToDoList(int id, ToDoListEntity newToDoListDetails) {
        ToDoListEntity toDoList = new ToDoListEntity();

        try{
            toDoList = toDoListRepository.findById(id).orElseThrow(() -> new RuntimeException("ToDo List ID " + id + " not found."));
            toDoList.setTitle(newToDoListDetails.getTitle());
            toDoList.setDescription(newToDoListDetails.getDescription());
            toDoList.setUpdatedAt(newToDoListDetails.getUpdatedAt());
        }catch(NoSuchElementException nex){
            throw new NameNotFoundException("Group "+id+"not found.");
        }finally{
            return toDoListRepository.save(toDoList);
        }

    }

    // Delete a to-do list
    public String deleteToDoList(int id) {
        if (toDoListRepository.existsById(id)) {
            toDoListRepository.deleteById(id);
            return "To-Do List successfully deleted.";
        } else {
            return "To-Do List with ID " + id + " not found!";
        }
    }

    //Get ToDoList By UserId
    public List<ToDoListEntity> getToDosByUserId(int userId) {
        return toDoListRepository.findByUserUserId(userId);
    }
}

