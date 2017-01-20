package com.sogeti.webshop;

import com.sogeti.entity.Customer;
import com.sogeti.entity.CustomerEJB;
import com.sogeti.entity.User;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.logging.Logger;

/**
 * Created by ROWAGEMA on 20-1-2017.
 */
@Named
@SessionScoped
public class AuthenticationController implements Serializable {
    private static final Logger LOG = Logger.getLogger(AuthenticationController.class.getName());
    @EJB
    private CustomerEJB customerEJB;

    private Customer customer;

    public AuthenticationController() {
        this.customer = new Customer();
        this.customer.setUser(new User());
    }

    public CustomerEJB getCustomerEJB() { return customerEJB; }

    public void setCustomerEJB(CustomerEJB customerEJB) { this.customerEJB = customerEJB; }

    public Customer getCustomer() { return customer; }

    public void setCustomer(Customer customer) { this.customer = customer; }

    public String register(AuthenticationController c) throws NoSuchAlgorithmException, NoSuchProviderException  {

        String passwordToHash = "password";
        byte[] salt = getSalt();

        String securePassword = getSecurePassword(passwordToHash, salt);

        //How to verify the hashed password with the new logged in password
//      String regeneratedPassowrdToVerify = getSecurePassword(passwordToHash, salt);

        Customer customer = c.getCustomer();
        customer.getUser().setPassword(securePassword);
        customer.getUser().setSalt(salt);

        customerEJB.addNew(c.getCustomer());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, " ", "Bedankt voor het registreren"));
        return "index";
    }


    private static String getSecurePassword(String passwordToHash, byte[] salt)
    {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(salt);
            //Get the hash's bytes
            byte[] bytes = md.digest(passwordToHash.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    //Add salt
    private static byte[] getSalt() throws NoSuchAlgorithmException, NoSuchProviderException
    {
        //Always use a SecureRandom generator
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        //Create array for salt
        byte[] salt = new byte[16];
        //Get a random salt
        sr.nextBytes(salt);
        //return salt
        return salt;
    }

}
