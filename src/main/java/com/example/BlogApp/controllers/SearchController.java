package com.example.BlogApp.controllers;

import com.example.BlogApp.models.Post;
import com.example.BlogApp.services.SearchService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    SearchService searchService;

    @GetMapping("/title")
    public ResponseEntity<List<Post>> searchByTitle(@RequestParam String titleSubstring){
        return ResponseEntity.status(HttpStatus.OK)
                .body(searchService.searchByTitle(titleSubstring));
    }

    @GetMapping("/tags")
    public ResponseEntity<List<Post>> searchByTags(@RequestBody Map<String,List<String>> req){
        return ResponseEntity.status(HttpStatus.OK)
                .body(searchService.searchByTags(req.get("tags")));
    }
}
