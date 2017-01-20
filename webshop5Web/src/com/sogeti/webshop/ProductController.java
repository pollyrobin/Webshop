package com.sogeti.webshop;

import com.sogeti.entity.*;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by ROWAGEMA on 29-12-2016.
 */
@ManagedBean(name = "productController", eager = true)
@RequestScoped
public class ProductController {
    private static final Logger LOG = Logger.getLogger(ProductController.class.getName());
    @EJB
    private ProductEJB productEJB;
    @EJB
    private CategoryEJB categoryEJB;

    private Product product = new Product();
    private List<Product> productList = new ArrayList<>();

    //@ManagedProperty(value="#{category}")
    private Category category;

    public List<Product> getProductList() {
        if(category != null) {
            productList = productEJB.findProductsByCategory(category);
        } else {
            productList = productEJB.findProducts();
        }
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

    public Category getCategory() { return category; }

    public void setCategory(Category category) { this.category = category; }

    public void prefillProducts() {
        Category c = new Category();
        c.setName("Hond");
        Category c1 = new Category();
        c1.setName("Kat");
        Category c2 = new Category();
        c2.setName("Paard");
        categoryEJB.addNew(c);
        categoryEJB.addNew(c1);
        categoryEJB.addNew(c2);

        Product p = new Product();
        p.setName("Royal Canin Selection Croc Evolution Gevogelte Vlees 20 kg");
        p.setDescription("Goede lichaamsconditie Volledige onderhoudsvoeding voor volwassen honden Smakelijke voeding Volledig evenwichtig recept");
        p.addCategory(c);
        p.addProductPrice(new ProductPrice(12.50, "euro"));
        product = productEJB.addNew(p);

        Product p2 = new Product();
        p2.setName("Krabpaal");
        p2.setDescription("Een krabpaal helpt voorkomen dat je kat gaat krabben aan jouw meubels. Bij Pets Place vind je een ruim assortiment krabpalen die geschikt zijn voor jouw poes of kater. Er zijn kleine krabplanken en krabpalen, maar ook grote krabmeubels waar jouw kat urenlang speelplezier aan beleeft.");
        p2.addCategory(c1);
        p2.addProductPrice(new ProductPrice(5, "euro"));
        product = productEJB.addNew(p2);

        Product p3 = new Product();
        p3.setName("Adori Speeltouw Klein 90 Gram Wit");
        p3.setDescription("Naturel Spelen Wit katoen Gebitsverzorging");
        p3.addCategory(c1);
        p3.addProductPrice(new ProductPrice(22.99, "euro"));
        product = productEJB.addNew(p3);

        Product p4 = new Product();
        p4.setName("Waldhausen Zadeldek Schapenvacht Zwart Allround");
        p4.setDescription("Dit product heeft een verwachte aankomsttijd van 1-2 werkdagen");
        p4.addCategory(c2);
        p4.addProductPrice(new ProductPrice(36.75, "euro"));
        product = productEJB.addNew(p4);
    }
}
