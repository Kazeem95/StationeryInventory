package com.stationery.beans;

import com.stationery.dao.UserDAO;
import com.stationery.model.User;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;
    private User loggedInUser;

    public String login() {
        UserDAO dao = new UserDAO();
        loggedInUser = dao.validateUser(username, password);
        if (loggedInUser != null) {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(true);
            session.setAttribute("loggedInUser", loggedInUser);
            session.setAttribute("username", loggedInUser.getUsername());
            return "home?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid username or password.", null));
            return null;
        }
    }

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        if (session != null) session.invalidate();
        loggedInUser = null;
        return "login?faces-redirect=true";
    }

    public boolean isLoggedIn() { return loggedInUser != null; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public User getLoggedInUser() { return loggedInUser; }
    public void setLoggedInUser(User u) { this.loggedInUser = u; }
}
