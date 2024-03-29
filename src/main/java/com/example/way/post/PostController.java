package com.example.way.post;

//import com.example.way.user.User;
//import com.example.way.user.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

//class PostData{
//    public String userid;
//    public Post post;
//
//    public String getUserid() {
//        return userid;
//    }
//
//    public void setUserid(String userid) {
//        this.userid = userid;
//    }
//
//    public Post getPost() {
//        return post;
//    }
//
//    public void setPost(Post post) {
//        this.post = post;
//    }
//}
@RestController
@RequestMapping(path= "api/v1/post")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {
    @Autowired
    private PostService postService;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    public PostController(PostService postService, UserRepository userRepository) {
//        this.postService = postService;
//        this.userRepository = userRepository;
//    }
//    @CrossOrigin()
    @GetMapping(path="get-post")
    public List<Post> getPosts() {
        return postService.getPosts();
    }
//    @CrossOrigin()
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(path = "get-my-post")
    public List<Post> getUserPosts() {
        return postService.getUserPosts();
    }
//    @CrossOrigin()
    @GetMapping(path = "get-post/{postId}")
    public Optional<Post> getPost(@PathVariable("postId") String postId) {
        return postService.getPost(postId);
    }
//    @CrossOrigin()
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping
    public ResponseEntity<Map<Object, Object>> registerNewPost(@RequestBody Post post) {
//        User user = userRepository.getById(postData.getUserid());
//        Post post = new Post();
//        post.setTitle(postData.getPost().getTitle());
//        post.setContent(postData.getPost().getContent());
//        post.setUser(user);
        return ResponseEntity.ok(postService.addNewPost(post));
    }
//    @CrossOrigin()
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping(path = "{postId}")
    public ResponseEntity<String> deletePost(@PathVariable("postId") String postId) {
        return ResponseEntity.ok(postService.deletePost(postId));
    }
//    @CrossOrigin()
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping()
    public ResponseEntity<String> updatePost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.updatePost(post));
    }
}
