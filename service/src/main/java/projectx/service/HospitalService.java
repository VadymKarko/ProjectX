package projectx.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import projectx.domain.Doctor;
import projectx.domain.Request;
import projectx.repository.connectors.MongoConnector;
import projectx.repository.dao.DoctorDAO;
import projectx.repository.dao.RequestDAO;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * @author proger
 * @since 5/23/15
 */
@Stateless
public class HospitalService {

    final Logger logger = LoggerFactory.getLogger(HospitalService.class);
    @EJB
    private MongoConnector connector;
    private DoctorDAO doctorDAO;
    private RequestDAO requestDAO;

    @PostConstruct
    private void init() {

//        doctorDAO = new DoctorDAO(MongoConnector.getMongo(),MongoConnector.getMorphia(), Constants.DATABASE_NAME);
       requestDAO = new RequestDAO();
    }

    public List<Request> getAllRequests() {
        List<Request>  result =requestDAO.findAllRequests();
        logger.info("sizeof requestlist in service layer :"+result.size());
        return requestDAO.findAllRequests();
    }

    public void saveRequest(Request request) {
        requestDAO.saveRequest(request);
    }

    public List<Doctor> getAllDoctors(){
       return doctorDAO.findAllDoctors();
    }

    public void saveDoctor(Doctor doctor){
        doctorDAO.saveDoctor(doctor);
    }


}
