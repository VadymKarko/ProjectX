package projectx.repository.wrappers;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import projectx.domain.Doctor;
import projectx.domain.Request;

import java.util.List;

/**
 * @author GreyFox12
 * @since 5/19/15
 */
public class DoctorWrapper {

    public static DBObject wrap(final Doctor doctor) {
        final BasicDBObject document = new BasicDBObject();
        document.put("firstname", doctor.getFirstName());
        document.put("lastname", doctor.getLastName());
        document.put("login", doctor.getLogin());
        document.put("password", doctor.getPassword());
        document.put("requests", doctor.getRequestList());
        return document;
    }

    public static Doctor unwrap(final DBObject document) {
        final Doctor doctor = new Doctor();

        doctor.setFirstName((String) document.get("firstname"));
        doctor.setLastName((String) document.get("lastname"));
        doctor.setLogin((String) document.get("login"));
        doctor.setPassword((String) document.get("password"));
        doctor.setRequestList((List<Request>) document.get("requets"));

        return doctor;
    }

}
