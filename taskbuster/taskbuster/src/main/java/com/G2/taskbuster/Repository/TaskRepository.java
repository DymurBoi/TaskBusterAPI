package com.G2.taskbuster.Repository;

import com.G2.taskbuster.Entity.Task;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByTitle(String title);
}
