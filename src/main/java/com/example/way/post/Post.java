package com.example.way.post;

import com.example.way.user.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class Post {
    private @Id @GeneratedValue Long postId;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private User user;
    @NonNull
    private String title;
    @NonNull
    private String content;
    private int upVotes;
    private int downVotes;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;
    public Post() {
    }

    public Post(@NonNull String title, @NonNull String content, int upVotes, int downVotes) {
        this.title = title;
        this.content = content;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
    }

    public Post(Long postId, @NonNull String title, @NonNull String content, int upVotes, int downVotes, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getContent() {
        return content;
    }

    public void setContent(@NonNull String content) {
        this.content = content;
    }

    public int getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    public int getDownVotes() {
        return downVotes;
    }

    public void setDownVotes(int downVotes) {
        this.downVotes = downVotes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
