package com.example.way.follow;

import com.example.way.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/follow")
public class FollowController {

    private final FollowService followService;

    @Autowired
    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @GetMapping()
    public String addFollow(@RequestBody String user1Id, @RequestBody String user2Id) throws NullPointerException{
        followService.saveFollow(user1Id,user2Id);
        return "Follow added successfully";
    }

//    @GetMapping()
//    public String addFollow(@RequestBody Long user1Id, @RequestBody Long user2Id) throws NullPointerException{
//        followService.saveFollow(user1Id,user2Id);
//        return "Friend added successfully";
//    }
//    function for removeFollow
    @DeleteMapping()
    public String removeFollow(@RequestBody String user1Id, @RequestBody String user2Id) throws NullPointerException{
        followService.removeFollow(user1Id,user2Id);
        return "Follow removed successfully";
    }

    @GetMapping("{userId}")
    public List<List<User>> getFollow(@PathVariable String userId) throws NullPointerException{
        return followService.getFollows(userId);
    }


}
