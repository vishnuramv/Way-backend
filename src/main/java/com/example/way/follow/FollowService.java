package com.example.way.follow;

import com.example.way.user.User;
import com.example.way.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class FollowService {

    private final FollowRepository followRepository;

    private final UserRepository userRepository;

    @Autowired
    public FollowService(FollowRepository followRepository, UserRepository userRepository) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }

    public void saveFollow(String userId) throws NullPointerException{
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("hello user--->>>" + userId);
        User user1 = userRepository.getById(user.getUsername());
        User user2 = userRepository.getById(userId);
        System.out.println("hello follow --->>>>> "+user1+" "+user2);
        Follow follow = new Follow();
        if( !(followRepository.existsByFirstUserAndSecondUser(user1, user2)) ){
            follow.setFirstUser(user1);
            follow.setSecondUser(user2);
            followRepository.save(follow);
            user1.setFollowing(user1.getFollowing()+1);
            user2.setFollowers(user2.getFollowers()+1);
            userRepository.save(user1);
            userRepository.save(user2);
        }
    }

    public HashMap<String,List<User>> getFollows(String userId){
        User user = userRepository.getById(userId);
        List<Follow> followers = followRepository.findByFirstUser(user);
        List<Follow> followings = followRepository.findBySecondUser(user);
        List<User> followersList = new ArrayList<>();
        List<User> followingsList = new ArrayList<>();
        HashMap<String,List<User>> output = new HashMap<>();
//        List<List<User>> output = new ArrayList<>();
        for (Follow follow : followers) {
            followingsList.add(userRepository.getById(follow.getSecondUser().getId()));
        }
        for (Follow follow : followings) {
            followersList.add(userRepository.getById(follow.getFirstUser().getId()));
        }
        output.put("followers", followersList);
        output.put("followings", followingsList);
        return output;

    }

//    function to remove a follow
    public void removeFollow(String userId) throws NullPointerException{
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user1 = userRepository.getById(user.getUsername());
        User user2 = userRepository.getById(userId);
        System.out.println("hello tester   --->>>" + userId);
        System.out.println("hello users        --->>>" + user1 + " " + user2);
        Boolean isFollow = followRepository.existsByFirstUserAndSecondUser(user1, user2);
        if(isFollow){
            Follow follow = followRepository.findByFirstUserAndSecondUser(user1, user2);
            System.out.println("follow ----->>>>>"+follow.getSecondUser().getId().equals(userId));
            followRepository.delete(follow);
            user1.setFollowing(user1.getFollowing()-1);
            user2.setFollowers(user2.getFollowers()-1);
            userRepository.save(user1);
            userRepository.save(user2);
        } else {
            throw new IllegalStateException("You are not following this user");
        }
    }
}
