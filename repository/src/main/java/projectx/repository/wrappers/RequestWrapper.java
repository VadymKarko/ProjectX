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
        document.put("firstName", request.getFirstName());
        document.put("lastName", request.getLastName());
        document.put("illnessDescription", request.getIllnessDescription());
        document.put("symptoms", request.getSymptoms());
        document.put("phoneNumber", request.getPhoneNumber());
        document.put("preferredDate", request.getPreferredDate());
        return document;
    }

    public static Request unwrap(final DBObject document) {
        final Request request = new Request();

        request.setFirstName((String) document.get("firstName"));
        request.setLastName((String) document.get("lastName"));
        request.setIllnessDescription((String) document.get("illnessDescription"));
        request.setSymptoms((String) document.get("symptoms"));
        request.setPhoneNumber((String) document.get("phoneNumber"));
        request.setPreferredDate((Date) document.get("preferredDate"));

        return request;
    }

}
