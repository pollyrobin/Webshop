package com.sogeti.entity;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ROWAGEMA on 14-12-2016.
 */
@ManagedBean(name = "customerController", eager = true)
@RequestScoped
public class CustomerController {
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

    public String addNewCustomer() {
        customer = customerEJB.addNew(customer);
        customerList = customerEJB.findCustomers();
        return "customerList.xhtml";
    }

    public String prefillCustomers() {
        //c1
        Customer c1 = new Customer();
        c1.setName("test_with_user");
        c1.setAddress("teststraat");
        c1.setCity("testcity");
        c1.setEmail("testemail");
        c1.setPostalCode("testpostcode");
        User u = new User();
        u.setUsername("testuser");
        u.setPassword("fakepass");
        u.setValidated(false);
        c1.setUser(u);

        //c2
        Customer c2 = new Customer();
        c2.setName("test_no_user");
        c2.setAddress("teststraat");
        c2.setCity("testcity");
        c2.setEmail("testemail2");
        c2.setPostalCode("testpostcode");

        //c3
        Customer c3 = new Customer();
        c3.setName("test_adminuser");
        c3.setAddress("teststraat");
        c3.setCity("testcity");
        c3.setEmail("testemail3");
        c3.setPostalCode("testpostcode");
        User u1 = new User(UserRole.UserRoleEnum.ADMIN);
        u1.setUsername("testadmin");
        u1.setPassword("another fake pass");
        u1.setValidated(true);
        c3.setUser(u1);

        String returnvalue = null;
        customer = customerEJB.addNew(c1);
        customer = customerEJB.addNew(c2);
        customer = customerEJB.addNew(c3);
        returnvalue = "Success!!!!!!!";

        return returnvalue;
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
