package com.example.way.post;

import com.example.way.user.User;
import com.example.way.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<Post> getPosts() {
        return postRepository.findAllByOrderByPostIdDesc();
    }
    public List<Post> getUserPosts(Long userID) {
        User user = userRepository.getById(userID);
        return postRepository.findPostByUserOrderByPostId(user);
    }



    public Optional<Post> getPost(Long postId) {
        return postRepository.findById(postId);
    }

    public void addNewPost(Post post) {
        postRepository.save(post);
    }

    public void deletePost(Long postId) {
        boolean exists = postRepository.existsById(postId);
        if (!exists) {
            throw new IllegalStateException("No post present by this id: " + postId);
        }
        postRepository.deleteById(postId);
    }

    @Transactional
    public void updatePost(Long postId, String title, String content) {
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
    }
}
