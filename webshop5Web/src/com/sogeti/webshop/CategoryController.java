package com.sogeti.webshop;

import com.sogeti.entity.Category;
import com.sogeti.entity.CategoryEJB;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by ROWAGEMA on 14-12-2016.
 */
@ManagedBean(name = "categoryController", eager = true)
@RequestScoped
public class CategoryController {
    @EJB
    private CategoryEJB categoryEJB;
    private Category category = new Category();
    private List<Category> parentCategories = new ArrayList<>();
    private List<Category> subCategories = new ArrayList<>();

    public List<Category> getParentCategories() {
        parentCategories = categoryEJB.findParentCategories();
        for (Category c: parentCategories) {
        }
        return parentCategories;
    }

    public List<Category> getSubCategories(long parentId) {
        subCategories = categoryEJB.findSubCategories(parentId);
        return subCategories;
    }

    public String viewCategory(){
        return "categoryList.xhtml";
    }

    public String addNewCategory() {
        category = categoryEJB.addNew(category);
        parentCategories = categoryEJB.findParentCategories();
        return "categoryList.xhtml";
    }

    public String prefillCategories() {
        String returnvalue = "Success!!!!!!!";
        Category c = new Category();
        c.setName("main category");
        category = categoryEJB.addNew(c);

        Category c1 = new Category();
        c1.setName("second category");
        category = categoryEJB.addNew(c1);

        Category c2 = new Category();
        c2.setName("third category");

        category = categoryEJB.addNew(c2);

        Category c3 = new Category();
        c3.setName("second category");
        c3.setParentId(c2.getId());

        category = categoryEJB.addNew(c3);

        return returnvalue;
    }

    public CategoryEJB getCategoryEJB() {
        return categoryEJB;
    }

    public void setCategoryEJB(CategoryEJB categoryEJB) {
        this.categoryEJB = categoryEJB;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
