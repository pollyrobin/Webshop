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
public class OrderEJB {
    @PersistenceContext(unitName = "MyPersistenceUnit")
    private EntityManager em;

    public List<Order> findOrders(){
        TypedQuery<Order> query = em.createNamedQuery("findAllOrders", Order.class);
        return query.getResultList();
    }

    public Order addNew(Order order) {
        em.persist(order);
        return order;
    }
}
