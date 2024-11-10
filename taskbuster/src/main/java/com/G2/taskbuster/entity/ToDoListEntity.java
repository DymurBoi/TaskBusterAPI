package com.G2.taskbuster.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class ToDoListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int toDoListID;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    //@JsonInclude(JsonInclude.Include.ALWAYS)
	private UserEntity user;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "toDoList", cascade = CascadeType.ALL)
    //@JsonManagedReference // Manage the reference
	private List<TaskEntity> task;
    
    // Constructors
    public ToDoListEntity() {
        super();
    }

    public ToDoListEntity(int toDoListID,String title, String description, LocalDateTime createdAt, LocalDateTime updatedAt, UserEntity user) {
        super();
        this.user=user;
        this.toDoListID = toDoListID;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and setters
    public int getToDoListID() {
        return toDoListID;
    }

    public void setToDoListID(int toDoListID) {
        this.toDoListID = toDoListID;
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

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }


    public UserEntity getUser(){
        return this.user;
    }
    public void setUser(UserEntity user){
        this.user=user;
    }
}
