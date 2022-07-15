package com.example.way.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path= "api/v1/post")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(path="get-post")
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @GetMapping(path = "user/{userID}")
    public List<Post> getUserPosts(@PathVariable("userID") String userID) {
        return postService.getUserPosts(userID);
    }

    @GetMapping(path = "{postId}")
    public Optional<Post> getPost(@PathVariable("postId") String postId) {
        return postService.getPost(postId);
    }

    @PostMapping
    public void registerNewPost(@RequestBody Post post) {
        postService.addNewPost(post);
    }

    @DeleteMapping(path = "{postId}")
    public void deletePost(@PathVariable("postId") String postId) {
        postService.deletePost(postId);
    }

    @PutMapping(path = "{postId}")
    public void updatePost(@PathVariable("postId") String postId, @RequestParam(required = false) String title, @RequestParam(required = false) String content) {
        postService.updatePost(postId, title, content);
    }
}
