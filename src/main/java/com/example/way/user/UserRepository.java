package com.example.way.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, String > {
    //    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findUserByEmail(String email);
    User findUserByUsername(String username);

//    @Transactional
//    @Modifying
//    @Query("update User u set u.email = ?1, u.username = ?2, u.name = ?3, u.dob = ?4, u.isPublic = ?5, u.password = ?6 , u.dpUrl= ?7" +
//            "where u.id = ?8")
//    void UpdateUser(@Nullable String email, @Nullable String username, @Nullable String name, @Nullable LocalDate dob, @Nullable boolean isPublic, @Nullable String password, @Nullable String dpUrl,String id);
    User findUserById(String id);
    Optional<User> findUserByEmailEquals(String email);
}

