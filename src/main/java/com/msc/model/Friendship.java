package com.msc.model;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Paul on 20/08/2017.
 */
@Entity
@Table(name = "friendship", catalog = "individual_project", uniqueConstraints = @UniqueConstraint(columnNames = { "user_one_id", "user_two_id" }))
public class Friendship implements Serializable{
    // Variables
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "friendship_id",
            unique = true, nullable = false)
    private Integer friendshipId;

    // One side of relationship is FetchType.Lazy but Many side must be FetchType.Eager because with lazy data
    // is loaded when needed but collections will not be loaded in time, therefore Many side must be eager or
    // org.hibernate.LazyInitializationException will be thrown
    // Many to one relationship with User Class
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_one_id", nullable = false)
    private User user1;

    // Many to one relationship with User Class
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_two_id", nullable = false)
    private User user2;

    // Many to one relationship with User Class
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "action_user_id", nullable = false)
    private User owner;

    // Status is the numerical representation of the status of the friend request
    // 0 = pending
    // 1 = accepted
    // 2 = declined
    // 3 = blocked
    // The design was inspired by http://www.codedodle.com/2014/12/social-network-friends-database.html
    @Column(name = "friendship_status", nullable = false)
    private int status;

    // Constructors
    public Friendship(){}

    public Friendship(User user1, User user2, int status, User owner) {
        this.user1 = user1;
        this.user2 = user2;
        this.status = status;
        this.owner = owner;
    }

    // Getters & Setters
    public Integer getFriendshipId() {
        return friendshipId;
    }

    public void setFriendshipId(Integer friendshipId) {
        this.friendshipId = friendshipId;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }


}
