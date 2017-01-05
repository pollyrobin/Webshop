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
public class CustomerEJB {
    @PersistenceContext(unitName = "MyPersistenceUnit")
    private EntityManager em;

    public List<Customer> findCustomers(){
        TypedQuery<Customer> query = em.createNamedQuery("findAllCustomers", Customer.class);
        return query.getResultList();
    }

    public Customer addNew(Customer customer) {
        em.persist(customer);
        return customer;
    }
}
