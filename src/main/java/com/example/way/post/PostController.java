package com.example.way.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @GetMapping(path = "{userID}")
    public List<Post> getUserPosts(@PathVariable("userID") Long userID) {
        return postService.getUserPosts(userID);
    }

    @GetMapping(path = "{postId}")
    public Optional<Post> getPost(@PathVariable("postId") Long postId) {
        return postService.getPost(postId);
    }

    @PostMapping
    public void registerNewPost(@RequestBody Post post) {
        postService.addNewPost(post);
    }

    @DeleteMapping(path = "{postId}")
    public void deletePost(@PathVariable("postId") Long postId) {
        postService.deletePost(postId);
    }

    @PutMapping(path = "{postId}")
    public void updatePost(@PathVariable("postId") Long postId, @RequestParam(required = false) String title, @RequestParam(required = false) String content) {
        postService.updatePost(postId, title, content);
    }
}
