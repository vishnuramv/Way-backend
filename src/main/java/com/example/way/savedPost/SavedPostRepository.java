package com.example.way.savedPost;

import com.example.way.post.Post;
import com.example.way.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavedPostRepository extends JpaRepository<SavedPost, String > {
    SavedPost findByUserAndPost(User user, Post post);
    List<SavedPost> findByUser(User user);
    boolean existsByUserAndPost(User user, Post post);
    void deleteByUserAndPost(User user, Post post);
}
