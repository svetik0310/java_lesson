
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
    @Column(name="id")
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

    public String getId() {
        return Integer.toString(id);
    }

}
