package projectx.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import projectx.domain.Doctor;
import projectx.domain.Request;
import projectx.repository.connectors.MongoConnector;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * @author proger
 * @since 5/23/15
 */
@Stateless
public class HospitalService {
    private static final Logger logger = LoggerFactory.getLogger(HospitalService.class);

    @EJB
    private MongoConnector connector;


    public List<Request> getAllRequests() {
        return connector.getRequests().find();
    }

    public void saveRequest(final Request request) {
        connector.getRequests().insert(request);
    }

    public List<Doctor> getAllDoctors(){
       return connector.getDoctors().find();
    }

    public void saveDoctor(final Doctor doctor){
        connector.getDoctors().insert(doctor);
    }
}
