package com.sogeti.webshop;

import com.sogeti.entity.Customer;
import com.sogeti.entity.Order;
import com.sogeti.entity.OrderEJB;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by rowagema on 12-1-2017.
 */
@Named
@RequestScoped
public class OrderCartController implements Serializable {

    @EJB
    private OrderEJB orderEJB;
    private Customer customer = new Customer();
    private ShoppingCartController shoppingCartController;

    public OrderCartController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        shoppingCartController
                = (ShoppingCartController) facesContext.getApplication()
                .createValueBinding("#{shoppingCartController}").getValue(facesContext);
    }

    public String getTest() {
        return "orderCart";
    }

    public void addOrder(Order order) {
        order = orderEJB.addNew(order);
    }

    public String order(CustomerController customerController) {
        Customer customer = customerController.getCustomer();
        Order order = shoppingCartController.getOrder();

        customer.addOrder(order);
        shoppingCartController.clearOrder();
        customerController.addCustomer(customer);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " ", "Bedankt voor het plaatsen van de bestelling"));
        return "index";
    }
}
