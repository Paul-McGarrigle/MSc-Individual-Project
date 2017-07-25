package Entities;

import javax.persistence.*;
import java.io.Serializable;

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

    @Column(name="username") private String username;
    @Column(name="password") private String password;

    public Users() {}

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
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

    @Override
    public String toString(){
        return "id="+id+", username="+username+", password="+password;
    }
}
