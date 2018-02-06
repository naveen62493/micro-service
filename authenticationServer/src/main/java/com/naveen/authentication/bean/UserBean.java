package com.naveen.authentication.bean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Naveen on 2/5/2018.
 */
@Entity
@Table(name="users")
public class UserBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name="username")
    @NotNull
    private String username;

    @Column(name="role")
    @NotNull
    private String role;

    @Column(name="password")
    @NotNull
    private String password;

    @OneToMany(mappedBy="user",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<UserToken> userToken = new HashSet<UserToken>(0);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Set<UserToken> getUserToken() {
        return userToken;
    }

    public void setUserToken(Set<UserToken> userToken) {
        this.userToken = userToken;
    }
}
