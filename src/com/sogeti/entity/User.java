package com.sogeti.entity;

import javax.persistence.*;

/**
 * Created by ROWAGEMA on 3-1-2017.
 */
@Entity
@Table( name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "password")
    private String password;

    @Column(name = "salt")
    private byte[] salt;

    @Column(name = "validated")
    private boolean validated;

    @OneToOne(cascade=CascadeType.ALL)
    private UserRole userRole;

    public User() {
        this.validated = false;
        this.userRole = new UserRole();
    }

    public User(UserRole.UserRoleEnum ure) {
        this.userRole = new UserRole(ure);
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

    public byte[] getSalt() { return salt; }

    public void setSalt(byte[] salt) { this.salt = salt; }
}
