package com.example.way.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String > {
    //    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findUserByEmail(String email);
}

