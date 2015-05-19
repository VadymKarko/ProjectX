package projectx.repository.wrappers;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import projectx.domain.Request;

import java.util.Date;

/**
 * @author GreyFox
 * @since 5/19/15
 */
public class RequestWrapper {

    public static DBObject wrap(final Request request) {
        final BasicDBObject document = new BasicDBObject();
        document.put("firstname", request.getFirstName());
        document.put("lastname", request.getLastName());
        document.put("illnessdescription", request.getIllnessDescription());
        document.put("symptoms", request.getSymptoms());
        document.put("phonenumber", request.getPhoneNumber());
        document.put("preferreddate", request.getPreferredDate());
        return document;
    }

    public static Request unwrap(final DBObject document) {
        final Request request = new Request();

        request.setFirstName((String) document.get("firstname"));
        request.setFirstName((String) document.get("lastname"));
        request.setIllnessDescription((String) document.get("illnessdescription"));
        request.setSymptoms((String) document.get("symptoms"));
        request.setPhoneNumber((String) document.get("phonenumber"));
        request.setPreferredDate((Date) document.get("preferreddate"));

        return request;
    }

}
