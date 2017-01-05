package com.sogeti.entity;

import sun.swing.BakedArrayList;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Currency;

/**
 * Created by rowagema on 4-1-2017.
 */

@Entity
@Table(name = "product")
@NamedQuery(name = "findAllProducts", query = "SELECT p FROM Product p")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    @Lob
    private String description;

    @Column(name = "image")
    private String image;

    @OneToMany(cascade=CascadeType.ALL)
    private Collection<ProductPrice> prices = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

    public Collection<ProductPrice> getPrices() {
        return prices;
    }

    public void addProductPrice(ProductPrice pp) {
        this.prices.add(pp);
    }
}
