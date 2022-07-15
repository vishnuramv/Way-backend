package com.example.way.savedPost;


import com.example.way.post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path= "api/v1/save-post")
public class SavedPostController {

    private final SavedPostService savedPostService;

    @Autowired
    public SavedPostController(SavedPostService savedPostService) {
        this.savedPostService = savedPostService;
    }

    @GetMapping(path = "save")
    public String savePost(@RequestBody String postId, @RequestBody String userId) throws NullPointerException{
        savedPostService.savePost(postId, userId);
        return "Follow added successfully";
    }

    @GetMapping(path= "get-posts")
    public List<Post> getSavedPosts(@RequestBody String userId) throws NullPointerException{
        return savedPostService.getSavedPosts(userId);
    }

    @DeleteMapping(path= "delete-post")
    public String deleteSavedPost(@RequestBody String postId, @RequestBody String userId) throws NullPointerException{
        savedPostService.deletePost(postId, userId);
        return "Follow removed successfully";
    }
}
