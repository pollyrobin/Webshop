package com.sogeti.entity;

import javax.persistence.*;

/**
 * Created by rowagema on 4-1-2017.
 */
@Entity
@Table( name = "category", uniqueConstraints= @UniqueConstraint(columnNames = {"name", "parent_id"} ))
@NamedQueries({
    @NamedQuery(name = "findAllParentCategories", query = "SELECT c FROM Category c WHERE c.parentId = null"),
    @NamedQuery(name = "findAllSubCategories", query = "SELECT c FROM Category c WHERE c.parentId = :parentId")})
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "parent_id")
    private long parentId;

    public Category() { }

    public Category(long parentId) {
        this.parentId = parentId;
    }

    public long getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }
}
