package projectx.web;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import projectx.domain.Doctor;
import projectx.domain.Request;
import projectx.service.AuthService;
import projectx.service.Cache;
import projectx.service.HospitalService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vadym
 * @since 5/23/15
 */
@ManagedBean
public class AssignPageBean implements Serializable {
    @EJB
    private HospitalService service;
    @EJB
    private AuthService authService;
    @EJB
    private Cache cache;
    private List<Request> list;
    private Request[] selected;

    private FacesContext context = FacesContext.getCurrentInstance();


    @PostConstruct
    public void init() {
        Doctor doctor = authService.getCurrentUser(getSessionId());
        if (doctor == null) return;

        if (!doctor.getRequestList().isEmpty()) {
            list = doctor.getRequestList();
            selected = list.toArray(new Request[list.size()]);
        }

        list = cache.getHazelcast().getList("AssignPageBean"+getSessionId());
        if (!list.isEmpty()) {
            selected = list.toArray(new Request[list.size()]);
        }
    }

    public Request[] getSelected() {
        return selected;
    }

    public List<Request> getSelectedList() {
        return list;
    }

    public void setSelected(Request[] selected) {
        this.selected = selected;
    }

    public void onSelect(SelectEvent event) {
        final Request item = (Request) event.getObject();
        list.add(item);
    }

    public void onUnselect(UnselectEvent event) {
        final Request item = (Request) event.getObject();
        list.remove(item);
    }


    public List<Request> getRequests() {
        // TODO: Foooo... Connections to DB should be decreased
        return service.getAllRequests();
    }

    public void save() {
        final Doctor doctor = authService.getCurrentUser(getSessionId());
        service.assignRequests(doctor, new ArrayList<Request>(list));
        list.clear();
    }

    private String getSessionId() {
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        return session.getId();
    }
}
