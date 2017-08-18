package com.msc.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by Paul on 24/07/2017.
 */
@Entity
@Table(name="users", catalog = "individual_project")
public class User implements Serializable {
    @Id// Assigns type as Primary Key
    @GeneratedValue(strategy= GenerationType.IDENTITY)// Used for Auto-Increment
    @Column(name="id")// Identifies Columns within Table
    private int id;

    @NotEmpty
    @Column(name="username") private String username;

    @NotEmpty
    @Column(name="password") private String password;

    @NotEmpty
    @Email
    @Column(name="email") private String email;

    @NotNull
    @Min(18) @Max(100)
    @Column(name="age") private Integer age;

    /*@NotNull
    @Column(name="gender") private Gender gender = Gender.UNDEFINED;*/

    //@DateTimeFormat(pattern="MM/dd/yyyy")
    //@NotNull
    //@Past
    @NotEmpty
    @Column(name="birthday") private String birthday;

    @Phone
    @Column(name="phone") private String phone;

    @NotEmpty
    @Column(name="country") private String country;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<UserRole> userRole = new HashSet<UserRole>(0);

    // Constructor generated by MyUserDetailsService Method
    /*public User(String username, String password, boolean enabled, boolean b, boolean b1, boolean b2, List<GrantedAuthority> authorities) {
    }*/

    public enum Gender {
        MALE, FEMALE, UNDEFINED
    }

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

    public User(String username, String password, String email, Integer age, String birthday, String phone, String country, boolean enabled, Set<UserRole> userRole) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        //this.gender = gender;
        this.birthday = birthday;
        this.phone = phone;
        this.country = country;
        this.enabled = enabled;
        this.userRole = userRole;
    }

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", gender=" +
                ", birthday=" + birthday +
                ", phone='" + phone + '\'' +
                ", country=" + country +
                '}';
    }
}
