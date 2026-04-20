package com.stationery.beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "sessionBean")
@SessionScoped
public class SessionBean implements Serializable {

    public boolean isLoggedIn() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        return session != null && session.getAttribute("loggedInUser") != null;
    }

    public String getLoggedInUsername() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            return (String) session.getAttribute("username");
        }
        return "";
    }

    public String getWelcomeMessage() {
        String u = getLoggedInUsername();
        return u.isEmpty() ? "" : "Welcome, " + u;
    }
}
