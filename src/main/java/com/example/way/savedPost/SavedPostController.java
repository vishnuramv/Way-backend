package com.example.way.savedPost;


import com.example.way.post.Post;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path= "api/v1/save-post")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SavedPostController {

    private final SavedPostService savedPostService;

    @Autowired
    public SavedPostController(SavedPostService savedPostService) {
        this.savedPostService = savedPostService;
    }

//    @CrossOrigin()
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping(path = "/save")
    public String savePost(@RequestBody String postId) throws NullPointerException{
        savedPostService.savePost(postId);
        return "Post-> "+ postId +" saved successfully";
    }

//    @CrossOrigin()
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(path= "/get-posts")
    public List<Post> getSavedPosts() throws NullPointerException{
        return savedPostService.getSavedPosts();
    }

//    @CrossOrigin()
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping(path= "delete-post/{postId}")
    public String deleteSavedPost(@PathVariable String postId) throws NullPointerException{
        savedPostService.deletePost(postId);
        return "Post-> "+ postId +" removed successfully";
    }

//    @CrossOrigin()
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping(path= "is-saved")
    public ResponseEntity<Map<Object, Object>> isSaved(@RequestBody String postId){
        System.out.println("postId: " + postId);
        return ResponseEntity.ok(savedPostService.isSaved(postId));
    }
}
