package com.example.way.follow;

import com.example.way.user.User;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "follow")
public class Follow {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", nullable = false, updatable = false)
    private String id;
    @CreatedDate
    private LocalDate createdDate;

    //following
    @ManyToOne(cascade = CascadeType.REMOVE)
    User firstUser;

    //followed
    @ManyToOne(cascade = CascadeType.REMOVE)
    User secondUser;

    public Follow(String id, User firstUser, User secondUser) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
