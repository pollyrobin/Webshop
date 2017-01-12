package com.sogeti.webshop;

import com.sogeti.entity.ShoppingCart;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.logging.Logger;

/**
 * Created by rowagema on 12-1-2017.
 */
@Named
@RequestScoped
public class OrderCartController {
    private static final Logger LOG = Logger.getLogger(OrderCartController.class.getName());

    ShoppingCart shoppingCart;

    public OrderCartController() { }

    public String getTest() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ShoppingCartController cart
                = (ShoppingCartController) facesContext.getApplication()
                .createValueBinding("#{shoppingCartController}").getValue(facesContext);
        LOG.info("" + cart.getOrderTotal());
        return "orderCart";
    }
}
