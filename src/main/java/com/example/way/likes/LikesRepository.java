package com.example.way.likes;

import com.example.way.post.Post;
import com.example.way.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import java.util.List;

@Repository
public interface LikesRepository extends JpaRepository<Likes, String > {
    Likes findByUserAndPost(User user, Post post);
//    List<Likes> findByUser(User user);
    boolean existsByUserAndPost(User user, Post post);
}
