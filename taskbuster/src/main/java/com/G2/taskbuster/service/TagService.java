package com.G2.taskbuster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.G2.taskbuster.entity.TagEntity;
import com.G2.taskbuster.repository.TagRepository;
import java.util.*;

import javax.naming.NameNotFoundException;
@Service
public class TagService {
    @Autowired
    TagRepository trepo;
    public TagService(){
        super();
    }
    public TagEntity postTag(TagEntity tag){
        return trepo.save(tag);
    }
    public List <TagEntity> getAllTag(){
        return trepo.findAll();
    } 
    @SuppressWarnings("finally")
    public TagEntity putTag(int tagId,TagEntity newTag){
        TagEntity tag=new TagEntity();
        try{
            tag=trepo.findById(tagId);
            tag.setName(newTag.getName());
            tag.setUpdatedAt(newTag.getUpdatedAt());
        }
        catch(NoSuchElementException nex){
            throw new NameNotFoundException("Tag Id "+tagId+" not found.");
        }
        finally{
            return trepo.save(tag);
        }
    }
    public String deleteTag(int id)
  {
    String msg="";
    if(trepo.findById(id)!=null)
    {
      trepo.deleteById(id);
      msg="Tag successfully deleted.";
    }
    else
    {
      msg=id+" NOT found";
    }
    return msg;
  }
}
