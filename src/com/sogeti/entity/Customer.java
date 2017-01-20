package com.sogeti.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ROWAGEMA on 14-12-2016.
 */

@Entity
@Table( name = "customer", uniqueConstraints= @UniqueConstraint(columnNames = {"email"} ))
@NamedQuery(name = "findAllCustomers", query = "SELECT c FROM Customer c")
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "city")
    private String city;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade=CascadeType.ALL)
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<Order> orders = new ArrayList<>();

    public Customer() {}
    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<Order> getOrders() { return orders; }

    public void setOrders(Collection<Order> orders) { this.orders = orders; }

    public void addOrder(Order order) {
        orders.add(order);
    }
}
