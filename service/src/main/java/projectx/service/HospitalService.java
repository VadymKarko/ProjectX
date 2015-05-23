package projectx.service;

import projectx.repository.impl.DoctorDAOImpl;
import projectx.repository.impl.RequestDAOImpl;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

/**
 * @author proger
 * @since 5/23/15
 */
@Stateless
public class HospitalService {
    private DoctorDAOImpl doctorDAOImpl = new DoctorDAOImpl();
    private RequestDAOImpl requestDAOImpl =new RequestDAOImpl();

    @PostConstruct
    private void init() {
        doctorDAOImpl = new DoctorDAOImpl();
        requestDAOImpl = new RequestDAOImpl();
    }

//    List<Request> getAllRequests(){
//        return requestDAOImpl.findAllRequests();
//    }



}
