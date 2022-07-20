package com.example.way.post;

import com.example.way.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "post_id", nullable = false, updatable = false)
    private String postId;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "id", nullable = false)
    @JsonIgnoreProperties(value = {"post", "hibernateLazyInitializer"})
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @NonNull
    private String title;
    @NonNull
    private String content;
    @Column(name = "topic", columnDefinition = "varchar(255) default 'General'")
    private String topic;
    @NonNull
    private String primaryImgUrl;
    private String secondaryImgUrl;
    private String tertiaryImgUrl;
    private int upVotes;
    private int downVotes;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime modifiedAt;
    public Post() {
    }

    public Post(@NonNull String title, @NonNull String content, int upVotes, int downVotes, String topic, @NonNull String primaryImgUrl, String secondaryImgUrl, String tertiaryImgUrl) {
        this.title = title;
        this.content = content;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
        this.topic = topic;
        this.primaryImgUrl = primaryImgUrl;
        this.secondaryImgUrl = secondaryImgUrl;
        this.tertiaryImgUrl = tertiaryImgUrl;
    }

    public Post(String postId, @NonNull String title, @NonNull String content, int upVotes, int downVotes, String topic, LocalDateTime createdAt, LocalDateTime modifiedAt, @NonNull String primaryImgUrl, String secondaryImgUrl, String tertiaryImgUrl) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.topic = topic;
        this.primaryImgUrl = primaryImgUrl;
        this.secondaryImgUrl = secondaryImgUrl;
        this.tertiaryImgUrl = tertiaryImgUrl;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @NonNull
    public String getPrimaryImgUrl() {
        return primaryImgUrl;
    }

    public void setPrimaryImgUrl(@NonNull String primaryImgUrl) {
        this.primaryImgUrl = primaryImgUrl;
    }

    public String getSecondaryImgUrl() {
        return secondaryImgUrl;
    }

    public void setSecondaryImgUrl(String secondaryImgUrl) {
        this.secondaryImgUrl = secondaryImgUrl;
    }

    public String getTertiaryImgUrl() {
        return tertiaryImgUrl;
    }

    public void setTertiaryImgUrl(String tertiaryImgUrl) {
        this.tertiaryImgUrl = tertiaryImgUrl;
    }
}
