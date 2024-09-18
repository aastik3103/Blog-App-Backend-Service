package com.example.BlogApp.repositories;

import com.example.BlogApp.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

    public Post findByTitle(String title);

    public List<Post> findByAuthor(String author);

    public List<Post> findByTitleContainingIgnoreCase(String titleSubstring);

    @Query("select p from Post p join p.tags t where t.name in ?1")
    public List<Post> findByTags(List<String> tags);

}
