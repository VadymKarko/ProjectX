package projectx.web;

import projectx.domain.Doctor;
import projectx.service.AuthService;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.AuthenticationException;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * @author proger
 * @since 5/26/15
 */
@ManagedBean
@RequestScoped
public class LoginBean implements Serializable {
    @EJB
    private AuthService service;

    private String login;
    private String password;

    private FacesContext context = FacesContext.getCurrentInstance();


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Doctor getCurrentUser() {
        return service.getCurrentUser(getSessionId());
    }


    public String signIn() {
        try {
            service.signIn(getSessionId(), login, password);
            return "/secured/requests.xhtml";

        } catch (AuthenticationException e) {
            // if authentication fails...
            context.addMessage("form", new FacesMessage("Invalid login or password!"));
            return "login.xhtml?faces-redirect=true";
        }
    }


    public String signOut() {
        service.signOut(getSessionId());
        return "login.xhtml?faces-redirect=true";
    }


    private String getSessionId() {
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        return session.getId();
    }
}
