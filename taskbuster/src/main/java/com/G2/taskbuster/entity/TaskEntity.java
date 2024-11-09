package com.G2.taskbuster.entity;

import java.time.LocalDateTime;

import org.springframework.scheduling.config.Task;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="task_entity")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;
    private String title;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime dueDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_tag_id")
    private TagEntity tag;
    public TagEntity getTag() {
        return tag;
    }
    public void setTag(TagEntity tag) {
        this.tag = tag;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "to_do_listid")
    private ToDoListEntity todoList;
    
    public ToDoListEntity getTodoList() {
        return todoList;
    }
    public void setTodoList(ToDoListEntity todoList) {
        this.todoList = todoList;
    }

    public TaskEntity(){
        super();
    }
    public TaskEntity(String title,String description, String status,LocalDateTime createdAt,LocalDateTime updatedAt,LocalDateTime dueDate, ToDoListEntity todolist){
        this.todoList = todolist;
        this.title=title;
        this.description=description;
        this.status=status;
        this.createdAt=createdAt;
        this.updatedAt=updatedAt;
        this.dueDate=dueDate;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    public LocalDateTime getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
    public int getTaskId() {
        return taskId;
    }
    
}
