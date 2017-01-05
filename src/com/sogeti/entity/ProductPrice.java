package com.sogeti.entity;

import javax.persistence.*;

/**
 * Created by rowagema on 4-1-2017.
 */
@Entity
public class ProductPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "price")
    private double price;

    @Column(name = "currency")
    private String currency;

    public ProductPrice() {}

    public ProductPrice(double price, String currency) {
        this.price = price;
        this.currency = currency;
    }
}
