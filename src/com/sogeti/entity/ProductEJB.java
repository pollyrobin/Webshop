package com.sogeti.entity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by ROWAGEMA on 29-12-2016.
 */
@Stateless
public class ProductEJB {
    @PersistenceContext(unitName = "MyPersistenceUnit")
    private EntityManager em;

    public List<Product> findProducts(){
        TypedQuery<Product> query = em.createNamedQuery("findAllProducts", Product.class);
        return query.getResultList();
    }

    public Product addNew(Product product) {
        em.persist(product);
        return product;
    }
}
