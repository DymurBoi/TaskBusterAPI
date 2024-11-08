package com.G2.taskbuster.Service;

import com.G2.taskbuster.Entity.Comment;
import com.G2.taskbuster.Entity.Task;
import com.G2.taskbuster.Repository.CommentRepository;
import com.G2.taskbuster.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TaskRepository taskRepository;

    public Comment addComment(Long taskId, String content) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        Comment comment = new Comment(content);
        task.addComment(comment); // Add the comment to the task
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByTaskId(Long taskId) {
        return commentRepository.findByTaskId(taskId);
    }
}
