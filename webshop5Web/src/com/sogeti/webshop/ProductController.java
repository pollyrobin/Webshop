package com.sogeti.webshop;

import com.sogeti.entity.*;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ROWAGEMA on 29-12-2016.
 */
@Named
@SessionScoped
public class ProductController implements Serializable {
    @EJB
    private ProductEJB productEJB;
    @EJB
    private CategoryEJB categoryEJB;

    private Product product = new Product();
    private List<Product> productList = new ArrayList<>();

    private Category category;

    public List<Product> getProductList() {
        return productList;
    }

    public String showProducts() {
        if(category != null) {
            productList = productEJB.findProductsByCategory(category);
            category = null;
        } else {
            productList = productEJB.findProducts();
        }
        return "index";
    }

    public String viewProduct(){
        return "index.xhtml";
    }

    public String addNewProduct() {
        product = productEJB.addNew(product);
        productList = productEJB.findProducts();
        return "index.xhtml";
    }

    public ProductEJB getProductEJB() {
        return productEJB;
    }

    public void setProductEJB(ProductEJB productEJB) {
        this.productEJB = productEJB;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Category getCategory() { return category; }

    public void setCategory(Category category) { this.category = category; }
}
