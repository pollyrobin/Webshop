package com.sogeti.webshop;

import com.sogeti.entity.Order;
import com.sogeti.entity.OrderLine;
import com.sogeti.entity.Product;
import com.sogeti.entity.ShoppingCart;

import javax.ejb.EJB;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;
import javax.enterprise.context.SessionScoped;
/**
 * Created by rowagema on 9-1-2017.
 */
@Named
@SessionScoped
public class ShoppingCartController implements Serializable {

    @EJB
    private ShoppingCart shoppingCart;

    public ShoppingCartController() {
        shoppingCart = new ShoppingCart();
    }


    public String addProduct(Product product, int amount) {
        shoppingCart.addProduct(product, amount);
        return "#";
    }

    public String changeProductAmount(Product product, int amount) {
        shoppingCart.addProduct(product, amount);
        return "shoppingCart";
    }

    public Order getOrder() {
        return shoppingCart.getOrder();
    }
    public void clearOrder() {
        shoppingCart.setOrder(new Order());
    }

    public Collection<OrderLine> getOrderLines() {
        return shoppingCart.getOrder().getOrderLines();
    }

    public double getOrderTotal() {
        return shoppingCart.getOrderTotal();
    }

    public void removeProduct(Product product) {
        shoppingCart.removeProduct(product);
    }
}
