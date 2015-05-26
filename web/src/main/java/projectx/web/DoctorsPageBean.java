package projectx.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import projectx.domain.Doctor;
import projectx.service.Cache;
import projectx.service.HospitalService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author proger
 * @since 5/26/15
 */
@Named("doctors")
@ManagedBean
public class DoctorsPageBean  implements Serializable{

    private static final Logger logger = LoggerFactory.getLogger(HospitalService.class);

    @EJB
    private HospitalService service;

    @EJB
    private Cache cache;
    private Map<String, Doctor> map;

    private Doctor doctor = new Doctor();


    @PostConstruct
    public void init() {
        map = cache.getHazelcast().getMap("DoctorsPageBean");
        doctor = map.get("doctor");
        if (doctor == null) doctor = new Doctor();
    }

    public Doctor getDocotor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
        map.put("doctor", doctor);
    }

    public List<Doctor> getDoctors() {
        // TODO: Foooo... Connections to DB should be decreased
        return service.getAllDoctors();
    }
}
