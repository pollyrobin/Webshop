package com.sogeti.webshop;

import com.sogeti.entity.Customer;
import com.sogeti.entity.CustomerEJB;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by ROWAGEMA on 14-12-2016.
 */
@Named
@SessionScoped
public class CustomerController implements Serializable {
    private static final Logger LOG = Logger.getLogger(AuthenticationController.class.getName());
    @EJB
    private CustomerEJB customerEJB;
    private Customer customer = new Customer();
    private List<Customer> customerList = new ArrayList<>();

    public List<Customer> getCustomerList() {
        customerList = customerEJB.findCustomers();
        return customerList;
    }

    public String viewCustomer(){
        return "customerList.xhtml";
    }
    public void addCustomer(Customer cs) {
        customer = customerEJB.addNew(cs);
    }
    public CustomerEJB getCustomerEJB() {
        return customerEJB;
    }
    public void setCustomerEJB(CustomerEJB customerEJB) {
        this.customerEJB = customerEJB;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
