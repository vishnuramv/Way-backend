package com.example.way.savedPost;


import com.example.way.post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SavedPostController {

    private final SavedPostService savedPostService;

    @Autowired
    public SavedPostController(SavedPostService savedPostService) {
        this.savedPostService = savedPostService;
    }

    @GetMapping()
    public String savePost(@RequestBody String postId, @RequestBody String userId) throws NullPointerException{
        savedPostService.savePost(postId, userId);
        return "Follow added successfully";
    }

    @GetMapping()
    public List<Post> getSavedPosts(@RequestBody String userId) throws NullPointerException{
        return savedPostService.getSavedPosts(userId);
    }

    @DeleteMapping()
    public String deleteSavedPost(@RequestBody String postId, @RequestBody String userId) throws NullPointerException{
        savedPostService.deletePost(postId, userId);
        return "Follow removed successfully";
    }
}
