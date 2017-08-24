package com.msc.model;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Paul on 21/08/2017.
 */
@Entity
@Table(name = "wall", catalog = "individual_project", uniqueConstraints = @UniqueConstraint(columnNames = { "wall_owner", "comment_owner" }))
public class Wall implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "wall_id",
            unique = true, nullable = false)
    private Integer wallId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinColumn(name = "wall_owner", nullable = false)
    private User wallOwner;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinColumn(name = "comment_owner", nullable = false)
    private User commentOwner;

    @Column(name = "wall_comment")
    private String comment;

    public Wall(){}

    public Wall(User wallOwner, User commentOwner, String comment) {
        this.wallOwner = wallOwner;
        this.commentOwner = commentOwner;
        this.comment = comment;
    }

    public Integer getWallId() {
        return wallId;
    }

    public void setWallId(Integer wallId) {
        this.wallId = wallId;
    }

    public User getWallOwner() {
        return wallOwner;
    }

    public void setWallOwner(User wallOwner) {
        this.wallOwner = wallOwner;
    }

    public User getCommentOwner() {
        return commentOwner;
    }

    public void setCommentOwner(User commentOwner) {
        this.commentOwner = commentOwner;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
