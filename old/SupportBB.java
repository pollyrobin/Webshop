package com.sogeti.webshop;

/**
 * Created by ROWAGEMA on 6-1-2017.
 */
import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named//("supportBB")
//@ManagedBean(name = "supportBB", eager = true)
@ConversationScoped()
//@ViewScoped
public class SupportBB implements Serializable {
    private static final long serialVersionUID = 1L;
    private String someValue;
    private String cid;

    @Inject
    private Conversation conversation;

    // Control start and end of conversation
    public void start() {
        conversation.begin();
        cid = conversation.getId();
        FacesContext context2 = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context2.getExternalContext().getSession(true);
        session.setAttribute("cid", cid);
    }

    public void end() {
        conversation.end();
        FacesContext context2 = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context2.getExternalContext().getSession(true);
        session.removeAttribute("cid");
    }

    // Navigation
    public String onClick() {
        if(someValue.equals("") || conversation == null) {
            return "";//Dont go anywhere if the there was no input the field
        }
        start();
        return "nextpage?cid="+conversation.getId();
    }

    public String onKeepGoing() {
        return "finish?cid="+conversation.getId();
    }

    public String onFinish() {
        end();// If triggered when there is no conversation(i.e URL Navigation)
        // there will be an exception
        return "index";
    }

    // Getters & Setters
    public String getSomeValue() {
        return someValue;
    }

    public void setSomeValue(String someValue) {
        this.someValue = someValue;
    }

    public String getCid() { return cid; }

    public void setCid(String cid) { this.cid = cid; }
}
