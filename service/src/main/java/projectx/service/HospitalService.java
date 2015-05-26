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
 * ToDo: JavaDoc
 *
 * @author vadym
 * @author vladimir
 * @since 5/23/15
 */
@Stateless
public class HospitalService {
    private static final Logger logger = LoggerFactory.getLogger(HospitalService.class);

    @EJB
    private MongoConnector connector;


    /**
     * ToDo:
     * @return
     */
    public List<Request> getAllRequests() {
        return connector.getRequests().find();
    }

    public boolean isPrimaryUp(){
        return connector.isMasterAlive();
    }

    /**
     * ToDo:
     * @param request
     */
    public void saveRequest(final Request request) {
        connector.getRequests().insert(request);
    }

    /**
     * ToDo:
     * @return
     */
    public List<Doctor> getAllDoctors(){
       return connector.getDoctors().find();
    }

    /**
     * ToDo:
     * @param doctor
     */
    public void saveDoctor(final Doctor doctor){
        connector.getDoctors().insert(doctor);
    }
}
