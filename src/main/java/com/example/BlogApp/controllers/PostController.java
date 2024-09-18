package com.example.BlogApp.controllers;

import com.example.BlogApp.models.Post;
import com.example.BlogApp.services.PostService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/add")
    public ResponseEntity<String>  addPost(@RequestBody JsonNode request){
        Post post = postService.addPost(request);
        if(post==null)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Post with this title already exists");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Post has been added successfully with id: " + post.getId());
    }

    @GetMapping("/view/all")
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/view")
    public ResponseEntity<List<Post>> getCurrentUserPosts(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(postService.getCurrentUserPosts());
    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePost(@RequestBody Post post){
        Post updatedPost = postService.updatePost(post);
        if(updatedPost==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("This post cannot be edited by this user.");

        return ResponseEntity.status(HttpStatus.OK)
                .body("The post has been edited successfully");
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable int id){
        String res = postService.deletePost(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(res);
    }
}
