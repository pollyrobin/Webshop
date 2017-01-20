package com.sogeti.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by rowagema on 4-1-2017.
 */

@Entity
@Table(name = "product")

@NamedQueries({
    @NamedQuery(name = "findAllProducts", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "findAllProductsByCategory", query = "SELECT distinct p FROM Product p join p.categories c where c.name = :name")
})
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    @Lob
    private String description;

    @Column(name = "image")
    private String image;

    @NotNull
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<ProductPrice> prices = new ArrayList<>();

    @NotNull
    @ManyToMany()
    private Collection<Category> categories = new ArrayList<>();

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

    public Collection<Category> getCategories() {
        return categories;
    }

    public void addCategory(Category c) {
        this.categories.add(c);
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrices(Collection<ProductPrice> prices) {
        this.prices = prices;
    }

    public ProductPrice getProductPrice() {
        Collection<ProductPrice> prices = getPrices();

        return prices.iterator().next();
    }

    @Override
    public boolean equals(Object obj) {
        Product product = (Product) obj;
        return product.getId() == id;
    }
}
