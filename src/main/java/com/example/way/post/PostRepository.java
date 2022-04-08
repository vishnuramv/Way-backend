package com.example.way.post;

import com.example.way.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long > {
    List<Post> findPostByUserOrderByPostId(User user);
    List<Post> findAllByOrderByPostIdDesc();
}
