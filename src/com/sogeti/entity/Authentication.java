package com.sogeti.entity;

import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * Created by ROWAGEMA on 23-1-2017.
 */
@Local
@Stateless
public class Authentication {
    private Customer customer;
    private boolean loggedIn;

    public boolean isLoggedIn() { return loggedIn; }
    public void setLoggedIn(boolean loggedIn) { this.loggedIn = loggedIn; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
}
