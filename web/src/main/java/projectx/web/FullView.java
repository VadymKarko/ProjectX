package projectx.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import projectx.domain.Request;
import projectx.service.HospitalService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * @author proger
 * @since 5/23/15
 */
@Named("home")
@ManagedBean
public class FullView implements Serializable{
    @EJB
    private HospitalService hospitalService;
    final Logger logger = LoggerFactory.getLogger(FullView.class);

    @PostConstruct
    private void init() {
        List<Request> requestList=load();
        logger.info("In method init of managed bean. array size:"+requestList.size());
    }

    public List<Request> load(){

        return hospitalService.getAllRequests();
    }

    public String hello(){
        return "OUTPUT:"+String.valueOf(load().size());
    }

}
