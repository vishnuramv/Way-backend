package com.example.way.savedPost;

import com.example.way.post.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavedPostRepository {
    List<Post> findByUserId(Long userId);
}
