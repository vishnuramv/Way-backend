package com.example.way.savedPost;

import com.example.way.post.Post;
import com.example.way.user.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "saved_post")
public class SavedPost {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", nullable = false, updatable = false)
    private String id;
    @ManyToOne(cascade = CascadeType.REMOVE)
    User user;

    @ManyToOne(cascade = CascadeType.DETACH)
    Post post;

    public SavedPost(String id, User user, Post post) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
