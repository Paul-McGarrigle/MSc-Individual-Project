package com.msc.model;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Paul on 12/08/2017.
 */
// This Class was entirely taken from http://www.mkyong.com/spring-security/spring-security-hibernate-annotation-example/
// it specifies the user roles associated with each user account for Authentication and security reasons
@Entity
@Table(name = "user_roles", catalog = "individual_project", uniqueConstraints = @UniqueConstraint(columnNames = { "role", "username" }))
public class UserRole{

    // Variables
    private Integer userRoleId;
    private User user;
    private String role;

    // Constructors
    public UserRole() {
    }

    public UserRole(User user, String role) {
        this.user = user;
        this.role = role;
    }

    // Getters & Setters
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_role_id",
            unique = true, nullable = false)
    public Integer getUserRoleId() {
        return this.userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    // Many to one relationship with User Class
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", nullable = false)
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) { this.user = user; }

    @Column(name = "role", nullable = false, length = 45)
    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
