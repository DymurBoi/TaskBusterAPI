package com.G2.taskbuster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.G2.taskbuster.entity.TaskEntity;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer>{
    public TaskEntity findById(int taskId);
}
