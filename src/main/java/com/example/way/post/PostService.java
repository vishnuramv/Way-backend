package com.example.way.post;

import com.example.way.user.User;
import com.example.way.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

//    @Autowired
//    public PostService(PostRepository postRepository, UserRepository userRepository) {
//        this.postRepository = postRepository;
//        this.userRepository = userRepository;
//    }

    public List<Post> getPosts() {
        return postRepository.findAllByOrderByPostIdDesc();
    }
    public List<Post> getUserPosts() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return postRepository.findPostByUserOrderByPostId(userRepository.getById(user.getUsername()));
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(PostService.class);



    public Optional<Post> getPost(String postId) {
        return postRepository.findById(postId);
    }

    public Map<Object, Object> addNewPost(Post post) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LOGGER.info("The user details are: {}", user.getUsername());
        post.setUser(userRepository.getById(user.getUsername()));
        postRepository.save(post);
        Map<Object, Object> response = new HashMap<>();
        response.put("message", "Post added successfully");
        response.put("postId", post.getPostId());
        response.put("code", 200);
        return response;
    }

    public String deletePost(String postId) {
        Optional<Post> post = postRepository.findById(postId);
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (post.isEmpty()) {
            throw new IllegalStateException("No post present by this id: " + postId);
        }
        System.out.println("user"+user.getUsername());
        if(post.get().getUser().getId().equals(user.getUsername())) {
            postRepository.deleteById(postId);
            return ("post-->"+postId+" deleted successfully by " + post.get().getUser());
        }
        else {
            throw new IllegalStateException("You are not authorized to delete this post");
        }
    }

    @Transactional
    public String updatePost(Post post) {
        Optional<Post> postOptional = postRepository.findById(post.getPostId());
        if (postOptional.isEmpty()) {
            throw new IllegalStateException("No post present by this id: " + post.getPostId());
        }
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("user update" + user.getUsername());
        if(postOptional.get().getUser().getId().equals(user.getUsername())) {
            if (post.getTitle() != null && post.getTitle().length() > 0 && !Objects.equals(post.getTitle(), postOptional.get().getTitle())) {
                postOptional.get().setTitle(post.getTitle());
            }
            if (post.getContent() != null && post.getContent().length() > 0 && !Objects.equals(post.getContent(), postOptional.get().getContent())) {
                postOptional.get().setContent(post.getContent());
            }
            if (post.getTopic() != null && post.getTopic().length() > 0 && !Objects.equals(post.getTopic(), postOptional.get().getTopic())) {
                postOptional.get().setTopic(post.getTopic());
            }
            if (post.getPrimaryImgUrl() != null && post.getPrimaryImgUrl().length() > 0 && !Objects.equals(post.getPrimaryImgUrl(), postOptional.get().getPrimaryImgUrl())) {
                postOptional.get().setPrimaryImgUrl(post.getPrimaryImgUrl());
            }
            if (post.getSecondaryImgUrl() != null && post.getSecondaryImgUrl().length() > 0 && !Objects.equals(post.getSecondaryImgUrl(), postOptional.get().getSecondaryImgUrl())) {
                postOptional.get().setSecondaryImgUrl(post.getSecondaryImgUrl());
            }
            if (post.getTertiaryImgUrl() != null && post.getTertiaryImgUrl().length() > 0 && !Objects.equals(post.getTertiaryImgUrl(), postOptional.get().getTertiaryImgUrl())) {
                postOptional.get().setTertiaryImgUrl(post.getTertiaryImgUrl());
            }
            if(post.getTopic() != null && post.getTopic().length() > 0 && !Objects.equals(post.getTopic(), postOptional.get().getTopic())) {
                postOptional.get().setTopic(post.getTopic());
            }
            postRepository.save(postOptional.get());
            return ("post-->"+post.getPostId()+" updated successfully by " + postOptional.get().getUser().getUsername());
        }
        else {
            throw new IllegalStateException("You are not authorized to update this post");
        }
    }


}
