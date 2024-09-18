package com.example.BlogApp.services;

import com.example.BlogApp.models.Tag;
import com.example.BlogApp.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public Tag findOrCreateTag(String name){
        Tag tag = tagRepository.findByName(name);
        if(tag!=null)
            return tag;

        Tag newTag = new Tag(name);
        return newTag;
    }
}
