package com.example.way.savedPost;


import com.example.way.post.Post;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path= "api/v1/save-post")
public class SavedPostController {

    private final SavedPostService savedPostService;

    @Autowired
    public SavedPostController(SavedPostService savedPostService) {
        this.savedPostService = savedPostService;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(path = "save")
    public String savePost(@RequestBody String postId) throws NullPointerException{
        savedPostService.savePost(postId);
        return "Post-> "+ postId +" saved successfully";
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(path= "get-posts")
    public List<Post> getSavedPosts() throws NullPointerException{
        return savedPostService.getSavedPosts();
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping(path= "delete-post")
    public String deleteSavedPost(@RequestBody String postId) throws NullPointerException{
        savedPostService.deletePost(postId);
        return "Post-> "+ postId +" removed successfully";
    }
}
