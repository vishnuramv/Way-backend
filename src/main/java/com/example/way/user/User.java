package com.example.way.user;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class User {
    private @Id @GeneratedValue Long id;
    private String name;
    private LocalDate dob;
    private String email;
    private int followers;
    private int following;

    public User(long id, String name, LocalDate dob, String email, int followers, int following) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.followers = followers;
        this.following = following;
    }

    public User(String name, LocalDate dob, String email, int followers, int following) {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.followers = followers;
        this.following = following;
    }

    public User() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

//    public int getAge() {
//        return Period.between(this.dob, LocalDate.now()).getYears();
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
}
