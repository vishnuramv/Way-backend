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

    public String saveFollow(String userId) throws NullPointerException{
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
        return "follow added successfully";
    }

    public HashMap<String,List<User>> getFollows(String userId){
        User user = userRepository.getById(userId);
        return getStringListHashMap(user);

    }

    public HashMap<String,List<User>> getMyFollows() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user1 = userRepository.getById(user.getUsername());
        return getStringListHashMap(user1);
    }

    private HashMap<String, List<User>> getStringListHashMap(User user1) {
        List<Follow> followers = followRepository.findByFirstUser(user1);
        List<Follow> followings = followRepository.findBySecondUser(user1);
        List<User> followersList = new ArrayList<>();
        List<User> followingsList = new ArrayList<>();
        HashMap<String, List<User>> output = new HashMap<>();
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
    public String removeFollow(String userId) throws NullPointerException{
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
            return "follow removed successfully";
        } else {
            throw new IllegalStateException("You are not following this user");
        }
    }
}
