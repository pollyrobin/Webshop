package com.sogeti.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by ROWAGEMA on 3-1-2017.
 */

@Entity
@Table( name = "user_role")
public class UserRole {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private long id;

    public enum UserRoleEnum {
        CUSTOMER,
        ADMIN;
    }

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRoleEnum userRoleEnum;

    public UserRoleEnum getUserRole() {
        return userRoleEnum;
    }
    public UserRole() {
        this.userRoleEnum = UserRoleEnum.CUSTOMER;
    }

    public UserRole(UserRoleEnum ure) {
        this.userRoleEnum = ure;
    }

}
