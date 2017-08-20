package com.msc.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Paul on 20/08/2017.
 */
@Entity
@Table(name = "friendship", catalog = "individual_project", uniqueConstraints = @UniqueConstraint(columnNames = { "user_one_id", "user_two_id" }))
public class Friendship {
    private Integer friendshipId;
    private User user1;
    private User user2;
    private int status;
    private User owner;

    public Friendship(){};

    public Friendship(User user1, User user2, int status, User owner) {
        this.user1 = user1;
        this.user2 = user2;
        this.status = status;
        this.owner = owner;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "friendship_id",
            unique = true, nullable = false)
    public Integer getFriendshipId() {
        return friendshipId;
    }

    public void setFriendshipId(Integer friendshipId) {
        this.friendshipId = friendshipId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_one_id", nullable = false)
    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_two_id", nullable = false)
    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    @Column(name = "friendship_status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "action_user_id", nullable = false)
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
