package com.sogeti.webshop;

import com.sogeti.entity.Product;
import com.sogeti.entity.ProductEJB;
import com.sogeti.entity.ProductPrice;

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

    public void prefillProducts() {
        Product p = new Product();
        p.setName("Royal Canin Selection Croc Evolution Gevogelte Vlees 20 kg");
        p.setDescription("Goede lichaamsconditie Volledige onderhoudsvoeding voor volwassen honden Smakelijke voeding Volledig evenwichtig recept");
        p.addProductPrice(new ProductPrice(12.50, "euro"));
        product = productEJB.addNew(p);

        Product p2 = new Product();
        p2.setName("Krabpaal");
        p2.setDescription("Een krabpaal helpt voorkomen dat je kat gaat krabben aan jouw meubels. Bij Pets Place vind je een ruim assortiment krabpalen die geschikt zijn voor jouw poes of kater. Er zijn kleine krabplanken en krabpalen, maar ook grote krabmeubels waar jouw kat urenlang speelplezier aan beleeft.");
        p2.addProductPrice(new ProductPrice(12.50, "euro"));
        product = productEJB.addNew(p2);

        Product p3 = new Product();
        p3.setName("Adori Speeltouw Klein 90 Gram Wit");
        p3.setDescription("Naturel Spelen Wit katoen Gebitsverzorging");
        p3.addProductPrice(new ProductPrice(12.50, "euro"));
        product = productEJB.addNew(p3);
    }
}
