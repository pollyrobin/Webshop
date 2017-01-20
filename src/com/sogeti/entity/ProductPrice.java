package com.sogeti.entity;

import javax.persistence.*;

/**
 * Created by rowagema on 4-1-2017.
 */
@Entity
public class ProductPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //@Column(name = "price", scale=2, precision = 2)
    @Column(name = "price", columnDefinition="Decimal(10,2)")
    private double price;

    @Column(name = "currency")
    private String currency;

    public ProductPrice() {}

    public ProductPrice(double price, String currency) {
        this.price = price;
        this.currency = currency;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
