package com.G2.taskbuster.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.G2.taskbuster.entity.TagEntity;
import com.G2.taskbuster.service.TagService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(method=RequestMethod.GET,path="/api/taskbuster")
public class TagController {
    @Autowired
    TagService tserv;
    @PostMapping("/postTag")
    public TagEntity postTag(@RequestBody TagEntity tag){
        return tserv.postTag(tag);
    }
    @GetMapping("/getTag")
    public List <TagEntity> getAllTag(){
        return tserv.getAllTag();
    } 
    @PutMapping("/putTag")
    public TagEntity putTag(@RequestParam int tagId,@RequestBody TagEntity newTag){
        return tserv.putTag(tagId, newTag);
    }
    @DeleteMapping("/deleteTag/{id}")
    public String deleteTag(@PathVariable int id){
        return tserv.deleteTag(id);
    }
}
