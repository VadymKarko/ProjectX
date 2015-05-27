package projectx.service;

import projectx.domain.Doctor;
import projectx.repository.connectors.MongoConnector;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.naming.AuthenticationException;
import java.util.Map;

/**
 * @author proger
 * @since 5/26/15
 */
@Singleton
public class AuthService {
    @EJB
    private MongoConnector connector;

    @EJB
    private Cache cache;
    private Map<String, Doctor> map;


    @PostConstruct
    public void init() {
        map = cache.getHazelcast().getMap("AuthService");
    }


    public Doctor signIn(String sessionId, String login, String password) throws AuthenticationException {
        if (isAuthorized(sessionId)) return map.get(sessionId);

        final Doctor doctor = connector.getDoctors().findOne("login =", login);
        if (doctor == null || !password.equals(doctor.getPassword()))
            throw new AuthenticationException("Authentication fails");

        map.put(sessionId, doctor);

        return doctor;
    }

    public void signOut(String sessionId) {
        if (isAuthorized(sessionId)) map.remove(sessionId);
    }

    public boolean isAuthorized(String sessionId) {
        return map.containsKey(sessionId);
    }

    public Doctor getCurrentUser(String sessionId) {
        return map.get(sessionId);
    }
}
