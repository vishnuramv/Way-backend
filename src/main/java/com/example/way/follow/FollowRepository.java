package com.example.way.follow;

import com.example.way.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow,Long> {
    boolean existsByFirstUserAndSecondUser(User first, User second);

    List<Follow> findByFirstUser(User user);
    List<Follow> findBySecondUser(User user);
}
