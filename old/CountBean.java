package com.sogeti.webshop;

/**
 * Created by ROWAGEMA on 10-1-2017.
 */
// CountBean.java
import java.util.logging.Logger;
import java.io.Serializable;
// for JSF
/*import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;*/
// for CDI
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
/** JSF

@ManagedBean
@SessionScoped
*/
/** CDI */
@Named
@SessionScoped

public class CountBean implements Serializable {
    private static final Logger LOG = Logger.getLogger(CountBean.class.getName());

    private int count;

    public CountBean() {
        LOG.info("CountBean#Initializing counter ...");
        count = 0;
    }

    public void countActionVoid() {
        LOG.info("CountBean#countActionVoid() - Increasing counter ...");
        count++;
    }

    public String countActionAndForward() {
        LOG.info("CountBean#countActionAndForward() - Increasing counter ...");
        count++;
        return "count";
    }

    public String countActionAndRedirect() {
        LOG.info("CountBean#countActionAndRedirect() - Increasing counter ...");
        count++;
        return "count?faces-redirect=true;";
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
