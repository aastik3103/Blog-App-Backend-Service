package com.example.BlogApp.services;

import com.example.BlogApp.models.Post;
import com.example.BlogApp.repositories.PostRepository;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    PostRepository postRepository;

    @Transactional
    public List<Post> searchByTitle(String titleSubstring) {
        return postRepository.findByTitleContainingIgnoreCase(titleSubstring);
    }

    @Transactional
    public List<Post> searchByTags(List<String> tags) {
        return postRepository.findByTags(tags);
    }
}
