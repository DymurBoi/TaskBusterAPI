package com.G2.taskbuster.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.G2.taskbuster.entity.CommentEntity;
import com.G2.taskbuster.service.CommentService;

@RestController
@RequestMapping(method=RequestMethod.GET,path="/api/taskbuster")
public class CommentController {
    private final CommentService cserv;
    @Autowired
    public CommentController(CommentService cserv){
        this.cserv=cserv;
    }
     @PostMapping("/postComment")
    public CommentEntity postComment(@RequestBody CommentEntity comment){
        return cserv.postComment(comment, comment.getTask().getTaskId());
    }
    @GetMapping("/getComment")
    public List<CommentEntity> getAllComment(){
        return cserv.getAllComment();
    }
    @PutMapping("/putComment")
    public CommentEntity putComment(@RequestParam int commentId, @RequestBody CommentEntity newComment){
        return cserv.putComment(commentId, newComment);
    }
    @DeleteMapping("/deleteComment/{id}")
    public String deleteComment(@PathVariable int id){
        return cserv.deleteComment(id);
    }
}
