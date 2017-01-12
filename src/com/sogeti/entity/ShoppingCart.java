package com.sogeti.entity;

import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * Created by rowagema on 9-1-2017.
 */
@Local
@Stateless
public class ShoppingCart {

    private Order order;

    public void addProduct(Product product, int amount) {
        order.addProduct(product, amount);
    }

    public ShoppingCart() {
        order = new Order();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public double getOrderTotal() {
        return order.getOrderTotal();
    }

    public void removeProduct(Product product) {
        order.removeProduct(product);
    }
}