package model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;


/**
 * Created by Paul on 24/07/2017.
 */
@Entity
@Table(name="users")
public class Users implements Serializable{
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

    @NotNull
    @Column(name="gender") private Gender gender;

    //@DateTimeFormat(pattern="MM/dd/yyyy")
    //@NotNull
    //@Past
    @NotEmpty
    @Column(name="birthday") private String birthday;

    @Phone
    @Column(name="phone") private String phone;

    @NotEmpty
    @Column(name="country") private String country;

    public enum Gender {
        MALE, FEMALE
    }

    public Users() {}

    public Users(String username, String password, String email, Integer age, Gender gender, String birthday, String phone, String country) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.birthday = birthday;
        this.phone = phone;
        this.country = country;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

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

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", phone='" + phone + '\'' +
                ", country=" + country +
                '}';
    }
}
