package com.sogeti.entity;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by rowagema on 9-1-2017.
 */
@Local
public interface ShoppingCartInterface {
    public void doSomething(List<Product> list);
}
