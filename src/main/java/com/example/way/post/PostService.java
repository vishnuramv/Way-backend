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

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
//        User user = userRepository.getById(userID);
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return postRepository.findPostByUserOrderByPostId(userRepository.getById(user.getUsername()));
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(PostService.class);



    public Optional<Post> getPost(String postId) {
        return postRepository.findById(postId);
    }

    public String addNewPost(Post post) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LOGGER.info("The user details are: {}", user.getUsername());
        post.setUser(userRepository.getById(user.getUsername()));
        postRepository.save(post);
        return ("post created successfully by " + post.getUser());
    }

    public String deletePost(String postId) {
        Optional<Post> post = postRepository.findById(postId);
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (post.isEmpty()) {
            throw new IllegalStateException("No post present by this id: " + postId);
        }
        if(post.get().getUser().getEmail().equals(user.getUsername())) {
            postRepository.deleteById(postId);
            return ("post-->"+postId+" deleted successfully by " + post.get().getUser());
        }
        else {
            throw new IllegalStateException("You are not authorized to delete this post");
        }
    }

    @Transactional
    public void updatePost(String postId, String title, String content) {
        Post post = postRepository.
                findById(postId).orElseThrow(
                        () -> new IllegalStateException("No Post present by this id: " + postId)
                );
        if (title != null && title.length() > 0 && !Objects.equals(post.getTitle(), title)) {
            post.setTitle(title);
        }
        if (content != null && content.length() > 0 && !Objects.equals(post.getContent(), content)) {
            post.setTitle(content);
        }
        postRepository.save(post);
    }
}
