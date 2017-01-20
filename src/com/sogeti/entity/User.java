package com.sogeti.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by ROWAGEMA on 3-1-2017.
 */
@Entity
@Table( name = "user", uniqueConstraints= @UniqueConstraint(columnNames = {"username"} ))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator="increment")
//    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "validated")
    private boolean validated;

    @OneToOne(cascade=CascadeType.ALL)
    private UserRole userRole;

    public User() {
        this.userRole = new UserRole();
    }

    public User(UserRole.UserRoleEnum ure) {
        this.userRole = new UserRole(ure);
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

    public boolean getValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public UserRole getUserRole() { return userRole; }

    public void setUserRole(UserRole userRole) { this.userRole = userRole; }
}
