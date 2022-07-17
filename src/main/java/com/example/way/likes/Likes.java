package com.example.way.likes;

import com.example.way.post.Post;
import com.example.way.user.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "liked_post")
public class Likes {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @ManyToOne(cascade = CascadeType.DETACH)
    User user;

    @ManyToOne(cascade = CascadeType.DETACH)
    Post post;

    public Likes(String id, User user, Post post) {
        this.id = id;
        this.user = user;
        this.post = post;
    }

    public Likes(User user, Post post) {
    }

    public Likes() {
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
