package com.sogeti.entity;

import javax.persistence.*;

/**
 * Created by ROWAGEMA on 10-1-2017.
 */
@Entity
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(cascade=CascadeType.ALL)
    private Product product;

    @Column(name = "amount")
    private int amount;

    protected OrderLine() {}

    public OrderLine(Product product, int amount) {
        this.amount = amount;
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
