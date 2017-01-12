package com.sogeti.entity;

import javax.persistence.*;
import java.util.*;

/**
 * Created by ROWAGEMA on 10-1-2017.
 */

@Entity
@Table( name = "order")
@NamedQuery(name = "findAllOrders", query = "SELECT o FROM Order o")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @OneToMany(cascade=CascadeType.ALL)
    private Collection<OrderLine> orderLines = new ArrayList<>();

    public Collection<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(Collection<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public void addProduct(Product product, int amount) {
        if(productIsNotOnOrder(product, amount)) {
            OrderLine orderLine = new OrderLine(product, amount);
            orderLines.add(orderLine);
        }
    }

    public boolean productIsNotOnOrder(Product product, int amount) {
        for(OrderLine o : orderLines) {
            if(o.getProduct().equals(product)) {
                if(o.getAmount()+amount > 0) {
                    o.setAmount(o.getAmount()+amount);
                } else {
                    removeProduct(product);
                }
                return false;
            }
        }
        return true;
    }

    public double getOrderTotal() {
        double total = 0;
        for(OrderLine o : orderLines) {
            total += o.getAmount()*o.getProduct().getProductPrice().getPrice();
        }
        return total;
    }

    public void removeProduct(Product product) {
        Iterator<OrderLine> iter = orderLines.iterator();

        while(iter.hasNext()) {
            OrderLine orderLine = iter.next();
            if(orderLine.getProduct().equals(product)) {
                iter.remove();
            }
        }
    }
}
