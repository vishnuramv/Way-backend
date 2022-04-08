package com.example.way.follow;

import com.example.way.user.User;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

public class Follow {
    private @Id @GeneratedValue Long id;
    @CreatedDate
    private LocalDate createdDate;

    @ManyToMany(cascade = CascadeType.REMOVE)
    User firstUser;

    @ManyToMany(cascade = CascadeType.REMOVE)
    User secondUser;

    public Follow(Long id, User firstUser, User secondUser) {
        this.id = id;
        this.firstUser = firstUser;
        this.secondUser = secondUser;
    }

    public Follow(User firstUser, User secondUser) {
        this.firstUser = firstUser;
        this.secondUser = secondUser;
    }

    public Follow() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public User getFirstUser() {
        return firstUser;
    }

    public void setFirstUser(User firstUser) {
        this.firstUser = firstUser;
    }

    public User getSecondUser() {
        return secondUser;
    }

    public void setSecondUser(User secondUser) {
        this.secondUser = secondUser;
    }
}
