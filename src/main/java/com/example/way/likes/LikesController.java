package com.example.way.likes;

//import com.example.way.post.Post;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import java.util.List;

@RestController
@RequestMapping(path= "api/v1/vote-post")
public class LikesController {
    @Autowired
    private LikesService likesService;

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(path = "upvote")
    public String upVotePosts(@RequestBody String postId) throws NullPointerException{
        likesService.upVotePosts(postId);
        return "Upvote post-> "+ postId +" successfully";
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping(path= "delete-upvote")
    public String unUpVotePosts(@RequestBody String postId) throws NullPointerException{
        likesService.unUpVotePosts(postId);
        return "UnUpVoted post-> "+ postId +" successfully";
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(path = "downvote")
    public String downVotePosts(@RequestBody String postId) throws NullPointerException{
        likesService.downVotePosts(postId);
        return "DownVoted post-> "+ postId +" successfully";
    }


    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping(path= "delete-downvote")
    public String unDownVotePosts(@RequestBody String postId) throws NullPointerException{
        likesService.unDownVotePost(postId);
        return "UnDownVoted post-> "+ postId +" successfully";
    }
}
