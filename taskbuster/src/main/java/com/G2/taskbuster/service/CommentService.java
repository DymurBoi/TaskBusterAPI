package com.G2.taskbuster.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.G2.taskbuster.entity.CommentEntity;
import com.G2.taskbuster.entity.TagEntity;
import com.G2.taskbuster.entity.TaskEntity;
import com.G2.taskbuster.entity.ToDoListEntity;
import com.G2.taskbuster.repository.CommentRepository;
import com.G2.taskbuster.repository.TagRepository;
import com.G2.taskbuster.repository.TaskRepository;
import com.G2.taskbuster.repository.ToDoListRepository;

@Service
public class CommentService {
    @Autowired
    CommentRepository crepo;
    @Autowired
    TaskRepository trepo;
    public CommentService(){
        super();
    }
    @Autowired
    public CommentService(CommentRepository crepo, TaskRepository trepo){
        this.crepo=crepo;
        this.trepo=trepo;
    }
    public CommentEntity postComment(CommentEntity comment,int taskId){
        try{
            TaskEntity task=trepo.findById(taskId);
            if(task !=null ){
                comment.setTask(task);
                return crepo.save(comment);
            }
            else{
                throw new RuntimeException("Task ID "+taskId+" not found.");
            }
        }
        catch(Exception e){
            throw new RuntimeException("Error occured while saving");
        }
    }
    public List<CommentEntity> getAllComment(){
        return crepo.findAll();
    }
    @SuppressWarnings("finally")
    public CommentEntity putComment(int commentId,CommentEntity newComment){
        CommentEntity comment=new CommentEntity();
        try{
            comment=crepo.findById(commentId);
            comment.setCommentText(newComment.getCommentText());
            comment.setTask(newComment.getTask());
        }
        catch(NoSuchElementException nex){
        throw new NameNotFoundException("Comment " + commentId + " not found.");
		}
        finally {
			return crepo.save(comment);
		}
    }
    public String deleteComment(int id){
    String msg="";
    if(crepo.findById(id)!=null)
    {
      crepo.deleteById(id);
      msg="Comment successfully deleted.";
    }
    else
    {
      msg=id+" NOT found";
    }
    return msg;
    }
}
