package com.sogeti.webshop;

import com.sogeti.entity.Authentication;
import com.sogeti.entity.Customer;
import com.sogeti.entity.CustomerEJB;
import com.sogeti.entity.User;

import javax.ejb.EJB;
import javax.inject.Named;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.logging.Logger;

/**
 * Created by ROWAGEMA on 20-1-2017.
 */
@Named
@SessionScoped
public class AuthenticationController implements Serializable {
    private static final Logger LOG = Logger.getLogger(AuthenticationController.class.getName());

    @EJB
    private Authentication authentication;

    @EJB
    private CustomerEJB customerEJB;

    private Customer customer;

    public AuthenticationController() {
        authentication = new Authentication();
        this.customer = new Customer();
        this.customer.setUser(new User());
    }

    public CustomerEJB getCustomerEJB() { return customerEJB; }
    public void setCustomerEJB(CustomerEJB customerEJB) { this.customerEJB = customerEJB; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Authentication getAuthentication() { return authentication; }
    public void setAuthentication(Authentication authentication) { this.authentication = authentication; }

    public String register(AuthenticationController c) throws NoSuchAlgorithmException, NoSuchProviderException  {

        Customer customer = c.getCustomer();
        customer.getUser().hashPassword();
        customerEJB.addNew(customer);
//        this.login(customer);
        /*this.customer = new Customer();
        this.customer.setUser(new User());*/

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " ", "Bedankt voor het registreren"));
        return "index";
    }

    public void login(Customer cus) {
        Customer customer1 = customerEJB.findCustomerByEmail(customer);
        String message = "Geen gebruiker gevonden met het ingevulde e-mailadres en wachtwoord";
        if(customer1 != null) {
            if(customer1.getUser().isPasswordMatch(cus.getUser().getPassword())) {
                authentication.setCustomer(customer1);
                authentication.setLoggedIn(true);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " ", "Succesvol ingelogd"));


                FacesContext facesContext = FacesContext.getCurrentInstance();
                CustomerController customerController
                        = (CustomerController) facesContext.getApplication()
                        .createValueBinding("#{customerController}").getValue(facesContext);
                customerController.setCustomer(customer1);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, " ", message));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, " ", message));
        }
    }

    public String logout() {
        authentication.setCustomer(null);
        authentication.setLoggedIn(false);

        FacesContext facesContext = FacesContext.getCurrentInstance();
        CustomerController customerController
                = (CustomerController) facesContext.getApplication()
                .createValueBinding("#{customerController}").getValue(facesContext);
        customerController.setCustomer(null);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " ", "Succesvol uitgelogd"));
        return "";
    }
}
