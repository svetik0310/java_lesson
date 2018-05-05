
package ru.zotkina.mantis.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="mantis_user_table")
public class UserData {
    @Expose
    @Column(name="username")
    private  String username;
    @Expose
    @Column(name="password")
    private String password;
    @Expose
    @Column(name="email")
    private String email;

    @Expose
    @Id
    private int id;



    public UserData withUsername(String username)
    {
        this.username= username;
        return this;
    }
    public UserData withId(int id)
    {
        this.id= id;
        return this;
    }
    public UserData withEmail(String email)
    {
        this.email= email;
        return this;
    }

    public UserData withPassword(String pass) {
        this.password = pass;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return Integer.toString(id);
    }

}
