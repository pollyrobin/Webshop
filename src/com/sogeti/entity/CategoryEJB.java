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
public class CategoryEJB {
    @PersistenceContext(unitName = "MyPersistenceUnit")
    private EntityManager em;

    public List<Category> findParentCategories(){
        TypedQuery<Category> query = em.createNamedQuery("findAllParentCategories", Category.class);
        return query.getResultList();
    }

    public List<Category> findSubCategories(long parentId){
        TypedQuery<Category> query = em.createNamedQuery("findAllSubCategories", Category.class)
            .setParameter("parentId", parentId);
        return query.getResultList();
    }

    public Category addNew(Category category) {
        em.persist(category);
        return category;
    }
}
