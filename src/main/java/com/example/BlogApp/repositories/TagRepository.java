package com.example.BlogApp.repositories;

import com.example.BlogApp.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag,Integer>{

    public Tag findByName(String name);
}
