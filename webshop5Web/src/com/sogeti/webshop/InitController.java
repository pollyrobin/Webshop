package com.sogeti.webshop;

import com.sogeti.entity.*;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * Created by ROWAGEMA on 23-1-2017.
 */
@Named
@RequestScoped
public class InitController {

    @EJB
    private ProductEJB productEJB;
    @EJB
    private CategoryEJB categoryEJB;

    @EJB
    private CustomerEJB customerEJB;

    Product product;

    public void init() throws NoSuchAlgorithmException, NoSuchProviderException {
        //Categories
        Category c = new Category();
        c.setName("Hond");
        Category c1 = new Category();
        c1.setName("Kat");
        Category c2 = new Category();
        c2.setName("Paard");
        categoryEJB.addNew(c);
        categoryEJB.addNew(c1);
        categoryEJB.addNew(c2);

        //Products
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

        //Customer / Users
        Customer customer = new Customer();
        customer.setPostalCode("8875BL");
        customer.setName("Robin Wageman");
        customer.setAddress("Kerkstraat 19");
        customer.setCity("Pannenkoekengat");
        customer.setEmail("t@t");
        customer.setUser(new User());
        customer.getUser().setPassword("test");
        customer.getUser().hashPassword();
        customerEJB.addNew(customer);
        //AuthenticationController auth = new AuthenticationController();
        //auth.login(customer);
    }
}
