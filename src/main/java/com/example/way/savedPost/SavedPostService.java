package com.example.way.savedPost;


import com.example.way.post.Post;
import com.example.way.post.PostRepository;
import com.example.way.user.User;
import com.example.way.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavedPostService {
    private final SavedPostRepository savedPostRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public SavedPostService(SavedPostRepository savedPostRepository, UserRepository userRepository, PostRepository postRepository) {
        this.savedPostRepository = savedPostRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public void savePost(String postId, String userId) throws NullPointerException{
        User user = userRepository.getById(userId);
        Post post = postRepository.getById(postId);

        SavedPost savedPost = new SavedPost();
        if( !(savedPostRepository.existsByUserAndPost(user, post)) ){
            savedPost.setPost(post);
            savedPost.setUser(user);
            savedPostRepository.save(savedPost);
        }
    }

    public void deletePost(String postId, String userId) throws NullPointerException{
        User user = userRepository.getById(userId);
        Post post = postRepository.getById(postId);

//        SavedPost savedPost = new SavedPost();
        if( savedPostRepository.existsByUserAndPost(user, post) ){
            savedPostRepository.deleteByUserAndPost(user, post);
        }
    }
    public List<Post> getSavedPosts(String userId){
        User user = userRepository.getById(userId);
        return savedPostRepository.findByUser(user);
    }
}

