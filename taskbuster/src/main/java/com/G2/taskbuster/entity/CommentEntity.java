package com.G2.taskbuster.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="comment_entity")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;
    @Column(name="comment_text", nullable = false)
    private String commentText;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "taskId")
    private TaskEntity task;
    public TaskEntity getTask() {
        return task;
    }
    public String getCommentText() {
        return commentText;
    }
    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public int getCommentId() {
        return commentId;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public void setTask(TaskEntity task) {
        this.task = task;
    }
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
