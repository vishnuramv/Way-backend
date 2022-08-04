package com.example.way.follow;

import com.example.way.user.User;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/follow")
public class FollowController {

    private final FollowService followService;

    @Autowired
    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(path = "add/{userId}")
    public ResponseEntity<String> addFollow(@PathVariable("userId") String userId) throws NullPointerException{
        return ResponseEntity.ok(followService.saveFollow(userId));
    }

//    @GetMapping()
//    public String addFollow(@RequestBody Long user1Id, @RequestBody Long user2Id) throws NullPointerException{
//        followService.saveFollow(user1Id,user2Id);
//        return "Friend added successfully";
//    }

    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("{userId}")
    public ResponseEntity<String> removeFollow(@PathVariable String userId) throws NullPointerException{
        System.out.println("Hello user ----->>>>    "+userId);
        return ResponseEntity.ok(followService.removeFollow(userId));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("{userId}")
    public ResponseEntity<HashMap<String,List<User>>> getFollow(@PathVariable String userId) throws NullPointerException{
        return ResponseEntity.ok(followService.getFollows(userId));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping()
    public ResponseEntity<HashMap<String,List<User>>> getMyFollows() throws NullPointerException{
        return ResponseEntity.ok(followService.getMyFollows());
    }


}
