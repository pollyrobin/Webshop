package com.sogeti.webshop;

import javax.faces.bean.*;

import java.io.Serializable;

@ManagedBean(name = "navigationController", eager = true)
@RequestScoped
public class NavigationController implements Serializable {

    //this managed property will read value from request parameter pageId
    @ManagedProperty(value="#{param.pageId}")
    private String pageId;

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String showPage(){
        int id = Integer.parseInt(pageId);
        String returnValue = null;
        switch (id) {
            case 1:
                returnValue = "home";
                break;
            case 2:
                returnValue = "about";
                break;
            case 3:
                returnValue = "customerList";
                break;
            case 5:
                returnValue = "productList";
                break;
            case 6:
                returnValue = "test";
                break;
            default:
                returnValue = "home";
                break;
        }
        return returnValue;
    }
}


