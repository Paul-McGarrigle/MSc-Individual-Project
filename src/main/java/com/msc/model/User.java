package com.msc.model;

import com.msc.validation.Phone;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by Paul on 24/07/2017.
 */
@Entity
@Table(name="users", catalog = "individual_project")
public class User implements Serializable {
    //Variables
    @Column(name="id")// Identifies Columns within Table
    private int id;

    //@NotEmpty & @NotNull etc. use Spring built in Validation Annotations
    @Id
    @NotEmpty
    @Column(name = "username", unique = true, nullable = false, length = 45)
    private String username;

    @NotEmpty
    @Column(name="password") private String password;

    @NotEmpty
    @Email
    @Column(name="email") private String email;

    @NotNull
    @Min(18) @Max(100)
    @Column(name="age") private Integer age;

    @NotEmpty
    @Column(name="birthday") private String birthday;

    // This variable will use the custom validation annotation @Phone specified in the validation package
    @Phone
    @Column(name="phone") private String phone;

    @NotEmpty
    @Column(name="country") private String country;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    // The various One to many relationships the User Class has with the other Classes in the schema
    // Cascade sorts out issue with removing records with foreign keys
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = { CascadeType.ALL })
    private Set<UserRole> userRole = new HashSet<UserRole>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user1", cascade = { CascadeType.ALL })
    private Set<Friendship> requestedFriends;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user2", cascade = { CascadeType.ALL })
    private Set<Friendship> receivedFriends;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wallOwner", cascade = { CascadeType.ALL })
    private Set<Wall> wallOwners;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "commentOwner", cascade = { CascadeType.ALL })
    private Set<Wall> commentOwners;

    // Constructors
    public User() {}

    public User(String username, String password, String email, Integer age, String birthday, String phone, String country) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.birthday = birthday;
        this.phone = phone;
        this.country = country;
    }

    public User(int id, String username, String password, String email, Integer age, String birthday, String phone, String country, boolean enabled, Set<UserRole> userRole, Set<Friendship> requestedFriends, Set<Friendship> receivedFriends, Set<Wall> wallOwners, Set<Wall> commentOwners) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.birthday = birthday;
        this.phone = phone;
        this.country = country;
        this.enabled = enabled;
        this.userRole = userRole;
        this.requestedFriends = requestedFriends;
        this.receivedFriends = receivedFriends;
        this.wallOwners = wallOwners;
        this.commentOwners = commentOwners;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    /*public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }*/

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<UserRole> getUserRole() {
        return this.userRole;
    }

    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }

    public Set<Friendship> getRequestedFriends() {
        return requestedFriends;
    }

    public void setRequestedFriends(Set<Friendship> requestedFriends) {
        this.requestedFriends = requestedFriends;
    }

    public Set<Friendship> getReceivedFriends() {
        return receivedFriends;
    }

    public void setReceivedFriends(Set<Friendship> receivedFriends) {
        this.receivedFriends = receivedFriends;
    }

    public Set<Wall> getWallOwners() {
        return wallOwners;
    }

    public void setWallOwners(Set<Wall> wallOwners) {
        this.wallOwners = wallOwners;
    }

    public Set<Wall> getCommentOweners() {
        return commentOwners;
    }

    public void setCommentOweners(Set<Wall> commentOweners) {
        this.commentOwners = commentOweners;
    }
}

