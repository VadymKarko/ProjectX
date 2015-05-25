package projectx.web;

import projectx.service.SessionService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.List;

/**
 * JSF Managed Bean. Stores information from the web page.
 * Invokes service layer.
 * WARNING!: THIS IS TUTORIAL
 *
 * @author vadym
 * @since 5/16/15
 */
@Deprecated
@ManagedBean
public class MainBean implements Serializable {
    @EJB
    private SessionService service;
    private String message;

    public List<String> getMessages() {
        return service.getMessage();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void add() {
        service.addMessage(message);
    }

    public List<String> getMembers() {
        return service.getClusterInfo();
    }
}
