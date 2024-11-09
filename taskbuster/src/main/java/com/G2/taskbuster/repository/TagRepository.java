package com.G2.taskbuster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.G2.taskbuster.entity.TagEntity;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Integer>{
    public TagEntity findById(int tagId);
}
