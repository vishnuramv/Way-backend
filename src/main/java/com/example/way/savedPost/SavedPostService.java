package com.example.way.savedPost;


import com.example.way.post.Post;
import com.example.way.post.PostRepository;
import com.example.way.user.User;
import com.example.way.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public void savePost(String postId) throws NullPointerException{
        UserDetails userreq = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = postRepository.getById(postId);
        User user = userRepository.getById(userreq.getUsername());
        SavedPost savedPost = new SavedPost();
        if( !(savedPostRepository.existsByUserAndPost(user, post)) ){
            savedPost.setPost(post);
            savedPost.setUser(user);
            savedPostRepository.save(savedPost);
        }
    }

    public void deletePost(String postId) throws NullPointerException{
        UserDetails userreq = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.getById(userreq.getUsername());
        Post post = postRepository.getById(postId);
        if( savedPostRepository.existsByUserAndPost(user, post) ){
            SavedPost savedPost = savedPostRepository.findByUserAndPost(user, post);
            savedPostRepository.delete(savedPost);
        } else {
            throw new NullPointerException("Post not found");
        }
    }
    public List<Post> getSavedPosts(){
        UserDetails userreq = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.getById(userreq.getUsername());
        System.out.println("Hello Saved posts \n\n");
        List<SavedPost> savedPosts = savedPostRepository.findByUser(user);
        System.out.println("saved posts list--->>>"+savedPosts);
        List<Post> posts = new ArrayList<>();
        for(SavedPost savedPost : savedPosts){
            posts.add(savedPost.getPost());
        }
        System.out.println("saved posts--->>>"+posts);
        return posts;
    }
}

