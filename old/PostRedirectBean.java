package com.sogeti.webshop;

/**
 * Created by ROWAGEMA on 6-1-2017.
 */
import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

@ManagedBean(name = "postRedirectBean", eager = true)
@ConversationScoped
public class PostRedirectBean implements Serializable{
    private static final long serialVersionUID = -1209535053351028792L;

    private Date date = new Date();

    @Inject
    private Conversation conversation;

    public String toViewsDemo() {

        if (conversation.isTransient()) {
            conversation.begin();
        }

        return "viewsDemo?faces-redirect=true";
    }

    public String toSecond() {

        if (conversation.isTransient()) {
            conversation.begin();
        }

        return "second?faces-redirect=true";
    }

    public Date getDate() {
        return date;
    }
}
