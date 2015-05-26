package projectx.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import projectx.domain.Request;
import projectx.service.Cache;
import projectx.service.HospitalService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author vadym
 * @since 5/23/15
 */
@ManagedBean
public class RequestsPageBean implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(HospitalService.class);

    @EJB
    private HospitalService service;

    @EJB
    private Cache cache;
    private Map<String, Request> map;

    private Request request = new Request();


    @PostConstruct
    public void init() {
        map = cache.getHazelcast().getMap("RequestsPageBean");
        request = map.get("request");
        if (request == null) request = new Request();
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
        map.put("request", request);
    }

    public List<Request> getRequests() {
        // TODO: Foooo... Connections to DB should be decreased
        return service.getAllRequests();
    }

    public void addRequest() {
        service.saveRequest(request);
        setRequest(new Request());
    }
}
