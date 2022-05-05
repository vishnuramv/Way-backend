package com.example.way.follow;

import com.example.way.user.User;
import com.example.way.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public void saveFollow(String user1Id, String user2Id) throws NullPointerException{
        User user1 = userRepository.getById(user1Id);
        User user2 = userRepository.getById(user2Id);

        Follow follow = new Follow();
        if( !(followRepository.existsByFirstUserAndSecondUser(user1, user2)) ){
            follow.setFirstUser(user1);
            follow.setSecondUser(user2);
            followRepository.save(follow);
        }
    }

    public List<List<User>> getFollows(String userId){
        User user = userRepository.getById(userId);
        List<Follow> followers = followRepository.findByFirstUser(user);
        List<Follow> followings = followRepository.findBySecondUser(user);
        List<User> followersList = new ArrayList<>();
        List<User> followingsList = new ArrayList<>();
        List<List<User>> output = new ArrayList<>();
        for (Follow follow : followers) {
            followersList.add(userRepository.getById(follow.getSecondUser().getId()));
        }
        for (Follow follow : followings) {
            followingsList.add(userRepository.getById(follow.getFirstUser().getId()));
        }
        output.add(followersList);
        output.add(followingsList);
        return output;

    }

//    function to remove a follow
    public void removeFollow(String user1Id, String user2Id) throws NullPointerException{
        User user1 = userRepository.getById(user1Id);
        User user2 = userRepository.getById(user2Id);
        Follow follow = followRepository.findByFirstUserAndSecondUser(user1, user2);
        followRepository.delete(follow);
    }
}
