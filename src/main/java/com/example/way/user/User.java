package com.example.way.user;


import com.example.way.follow.Follow;
import com.example.way.post.Post;
import com.example.way.savedPost.SavedPost;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "profile")
public class User {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @Column(name = "dp_url")
    private String dpUrl;


    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "dob")
    private LocalDate dob;
    @Column(name="isPublic", columnDefinition = "boolean default true")
    private boolean isPublic;
    @Column(name = "password")
    private String password;
    @Column(name = "followers")
    private int followers;
    @Column(name = "following")
    private int following;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> posts;

    @OneToMany(mappedBy = "secondUser")
    @JsonIgnore
    private List<Follow> followedUsers;

    @OneToMany(mappedBy = "firstUser")
    @JsonIgnore
    private List<Follow> followingUsers;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<SavedPost> savedPosts;


    public User(String id, String name, LocalDate dob, String email, String password, int followers, int following, String username, String dpUrl, boolean isPublic) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.followers = followers;
        this.following = following;
        this.username = username;
        this.dpUrl = dpUrl;
        this.isPublic = isPublic;
    }

    public User(String name, LocalDate dob, String email, String password, int followers, int following, String username, String dpUrl, boolean isPublic) {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.followers = followers;
        this.following = following;
        this.username = username;
        this.dpUrl = dpUrl;
        this.isPublic = isPublic;
    }

    public User() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public int getAge() {
//        return Period.between(this.dob, LocalDate.now()).getYears();
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }


    public String getDpUrl() {
        return dpUrl;
    }

    public void setDpUrl(String dpUrl) {
        this.dpUrl = dpUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

//    public List<Post> getPosts() {
//        return posts;
//    }
//
//    public void setPosts(List<Post> posts) {
//        this.posts = posts;
//    }

    public List<Follow> getFollowedUsers() {
        return followedUsers;
    }

    public void setFollowedUsers(List<Follow> followedUsers) {
        this.followedUsers = followedUsers;
    }

    public List<Follow> getFollowingUsers() {
        return followingUsers;
    }

    public void setFollowingUsers(List<Follow> followingUsers) {
        this.followingUsers = followingUsers;
    }

    public List<SavedPost> getSavedPosts() {
        return savedPosts;
    }

    public void setSavedPosts(List<SavedPost> savedPosts) {
        this.savedPosts = savedPosts;
    }
}