package com.G2.taskbuster.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.G2.taskbuster.entity.TagEntity;
import com.G2.taskbuster.entity.TaskEntity;
import com.G2.taskbuster.entity.ToDoListEntity;
import com.G2.taskbuster.repository.TagRepository;
import com.G2.taskbuster.repository.TaskRepository;
import com.G2.taskbuster.repository.ToDoListRepository;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskrepo;
    @Autowired
    TagRepository tagrepo;
    @Autowired
    ToDoListRepository toDoListRepository;
    public TaskService(){
        super();
    }
    @Autowired
    public TaskService(TaskRepository taskrepo, TagRepository tagrepo, ToDoListRepository toDoListRepository){
        this.taskrepo=taskrepo;
        this.tagrepo=tagrepo;
        this.toDoListRepository = toDoListRepository;
    }
    public TaskEntity postTask(TaskEntity task,int tagId,int todoId){
        try{
            ToDoListEntity todolist = toDoListRepository.findById(todoId);
            TagEntity tag=tagrepo.findById(tagId);
            if(todolist !=null && tag != null){
                task.setToDoList(todolist);
                task.setTag(tag);
                return taskrepo.save(task);
            }
            else{
                throw new RuntimeException("Tag ID "+tagId+" not found or To Do List ID "+todoId+"not found.");
            }
        }
        catch(Exception e){
            throw new RuntimeException("Error occured while saving");
        }
    }
    public List<TaskEntity> getAllTasks(){
        return taskrepo.findAll();
    }

    public List<TaskEntity> getTaskByToDoListID(int toDoListID){
        return taskrepo.findByToDoListToDoListID(toDoListID);
    }

    @SuppressWarnings("finally")
    public TaskEntity putTask(int taskId, TaskEntity newTask){
        TaskEntity task=new TaskEntity();
        try{
            task=taskrepo.findById(taskId);
            task.setDescription(newTask.getDescription());
            task.setDueDate(newTask.getDueDate());
            task.setStatus(newTask.getStatus());
            task.setTitle(newTask.getTitle());
            task.setUpdatedAt(newTask.getUpdatedAt());
                if(newTask.getTag()!=null){
                    TagEntity newTagDetails = newTask.getTag();

            if (task.getTag() != null) {
                TagEntity existingTag = task.getTag();
                existingTag.setName(newTagDetails.getName());
                existingTag.setUpdatedAt(newTagDetails.getUpdatedAt());

                // Save the updated TagEntity
                tagrepo.save(existingTag);
            } else {
                TagEntity newTag = newTagDetails;
                task.setTag(newTag);
                tagrepo.save(newTag);
            }
        }
    }
    catch(NoSuchElementException nex){
			throw new NameNotFoundException("Task " + taskId + " not found.");
		}finally {
			return taskrepo.save(task);
		}
    }
    public String deleteTask(int id)
  {
    String msg="";
    if(taskrepo.findById(id)!=null)
    {
      taskrepo.deleteById(id);
      msg="Task successfully deleted.";
    }
    else
    {
      msg=id+" NOT found";
    }
    return msg;
  }
  public TaskEntity getTaskById(int taskId) {
    try {
        TaskEntity task = taskrepo.findById(taskId);
        if (task != null) {
            return task;
        } else {
            throw new NoSuchElementException("Task not found with ID " + taskId);
        }
    } catch (NoSuchElementException e) {
        throw new RuntimeException("Error occurred while fetching task by ID: " + taskId, e);
    }
}

    //Returns number of Task
    public int getTaskCount() {
        return (int) taskrepo.count();
    }
    
}
