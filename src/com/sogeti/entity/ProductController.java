package com.sogeti.entity;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ROWAGEMA on 29-12-2016.
 */
@ManagedBean(name = "productController", eager = true)
@RequestScoped
public class ProductController {
    @EJB
    private ProductEJB productEJB;
    private Product product = new Product();
    private List<Product> productList = new ArrayList<>();

    public List<Product> getProductList() {
        productList = productEJB.findProducts();
        return productList;
    }

    public String viewProduct(){
        return "productList.xhtml";
    }

    public String addNewProduct() {
        product = productEJB.addNew(product);
        productList = productEJB.findProducts();
        return "productList.xhtml";
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
}
