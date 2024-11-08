package com.G2.taskbuster.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.G2.taskbuster.Entity.Comment;
import com.G2.taskbuster.Service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from your frontend app
public class CommentController {
    @Autowired
    private CommentService commentService;

    // Endpoint to add a comment to a task
    @PostMapping("/add/{taskId}")
    public ResponseEntity<Comment> addComment(@PathVariable Long taskId, @RequestBody String content) {
        Comment savedComment = commentService.addComment(taskId, content);
        return ResponseEntity.ok(savedComment);
    }

    // Endpoint to get comments for a specific task
    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Comment>> getCommentsByTaskId(@PathVariable Long taskId) {
        List<Comment> comments = commentService.getCommentsByTaskId(taskId);
        return ResponseEntity.ok(comments);
    }
}
