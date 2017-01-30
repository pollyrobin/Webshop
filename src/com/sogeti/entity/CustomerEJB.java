package com.sogeti.entity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.lang.reflect.Type;
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
        if (customer.getId() == 0) {
            em.persist(customer);
        } else {
            if (!em.contains(customer)) {
                em.merge(customer);
            }
        }
        return customer;
    }

    public Customer findCustomerByEmail(Customer customer) {
        TypedQuery<Customer> query = em.createNamedQuery("findCustomerByEmail", Customer.class)
            .setParameter("email", customer.getEmail());
        try {
            return query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
