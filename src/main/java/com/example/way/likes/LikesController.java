package com.example.way.likes;

//import com.example.way.post.Post;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//import java.util.List;

@RestController
@RequestMapping(path= "api/v1/vote-post")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LikesController {
    @Autowired
    private LikesService likesService;

//    @CrossOrigin()
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping(path = "upvote")
    public String upVotePosts(@RequestBody String postId) throws NullPointerException{
        System.out.println("postId: " + postId);
        likesService.upVotePosts(postId);
        return "Upvote post-> "+ postId +" successfully";
    }
//    @CrossOrigin()
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping(path= "delete-upvote/{postId}")
    public String unUpVotePosts(@PathVariable String postId) throws NullPointerException{
        likesService.unUpVotePosts(postId);
        return "UnUpVoted post-> "+ postId +" successfully";
    }
//    @CrossOrigin()
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping(path = "downvote")
    public String downVotePosts(@RequestBody String postId) throws NullPointerException{
        likesService.downVotePosts(postId);
        return "DownVoted post-> "+ postId +" successfully";
    }
//    @CrossOrigin()

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping(path = "is-voted")
    public ResponseEntity<Map<Object, Object>> isVoted(@RequestBody String postId) throws NullPointerException{
        return ResponseEntity.ok(likesService.isVoted(postId));
    }

//    @CrossOrigin()
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping(path= "delete-downvote")
    public String unDownVotePosts(@RequestBody String postId) throws NullPointerException{
        likesService.unDownVotePost(postId);
        return "UnDownVoted post-> "+ postId +" successfully";
    }
}
