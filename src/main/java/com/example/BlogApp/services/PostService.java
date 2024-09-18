package com.example.BlogApp.services;

import com.example.BlogApp.models.Post;
import com.example.BlogApp.models.Tag;
import com.example.BlogApp.repositories.PostRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostService {

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    @Autowired
    PostRepository postRepository;

    @Transactional
    public Post addPost(JsonNode request) {

        if(postRepository.findByTitle(request.get("title").asText())!=null)
            return null;

        Post post = new Post();
        ObjectMapper objectMapper = new ObjectMapper();

        post.setTitle(request.get("title").asText());
        post.setContent(request.get("content").asText());
        post.setAuthor(userService.getCurrentUser());
        post.setImageData(Base64.getDecoder().decode(request.get("image").asText()));
        Set<Tag> tags = objectMapper.convertValue(request.get("tags"), new TypeReference<Set<Tag>>() {});

        Set<Tag> savedTags = new HashSet<>();
        for(Tag tag: tags){
            Tag savedTag = tagService.findOrCreateTag(tag.getName());
            savedTags.add(savedTag);
        }

        post.setTags(savedTags);
       return  postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post updatePost(Post post) {
        Post currPost = postRepository.findById(post.getId()).orElse(null);
        if(currPost.getAuthor().equals(userService.getCurrentUser()))
            return postRepository.save(post);
        return null;
    }

    public String deletePost(int id) {
        Post currPost = postRepository.findById(id).orElse(null);
        if(!currPost.getAuthor().equals(userService.getCurrentUser()))
            return "This post cannot be deleted by the current user.";
        postRepository.deleteById(id);
        return "Post has been deleted successfully.";
    }

    @Transactional
    public List<Post> getCurrentUserPosts() {
        return postRepository.findByAuthor(userService.getCurrentUser());
    }
}
