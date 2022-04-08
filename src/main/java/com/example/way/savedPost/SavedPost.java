package com.example.way.savedPost;

import com.example.way.post.Post;
import com.example.way.user.User;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

public class SavedPost {
    private @Id
    @GeneratedValue
    Long id;

    @ManyToMany(cascade = CascadeType.REMOVE)
    User user;

    @ManyToMany(cascade = CascadeType.REMOVE)
    Post post;

    public SavedPost(Long id, User user, Post post) {
        this.id = id;
        this.user = user;
        this.post = post;
    }

    public SavedPost(User user, Post post) {
        this.user = user;
        this.post = post;
    }

    public SavedPost() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
