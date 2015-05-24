package projectx.repository.connectors;

import com.mongodb.MongoClient;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import projectx.domain.Doctor;
import projectx.domain.Request;
import projectx.repository.dao.DataProvider;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.Arrays;

/**
 * This is an example of repository
 * WARNING!: THIS IS TUTORIAL
 *
 * @author vadym
 * @since 5/17/15
 */
@Singleton
@Startup
public class MongoConnector {
    public static final String HOST_NAME = "localhost";
    public static final String DATABASE_NAME = "hospital";

    private MongoClient mongo;

    private DataProvider<Doctor> doctors;
    private DataProvider<Request> requests;


    @PostConstruct
    public void init() {
        try {
            // TODO: Hardcoded ports? Foooo...horrible coding style. Fix!
            mongo = new MongoClient(Arrays.asList(
                    new ServerAddress(HOST_NAME, 27018),
                    new ServerAddress(HOST_NAME, 27019),
                    new ServerAddress(HOST_NAME, 27020)
            ));

            mongo.setReadPreference(ReadPreference.secondary());

            final Morphia morphia = new Morphia();
            final Datastore datastore = morphia.createDatastore(mongo, DATABASE_NAME);
            morphia.map(Doctor.class, Request.class);

            doctors = new DataProvider<Doctor>(Doctor.class, datastore);
            requests = new DataProvider<Request>(Request.class, datastore);

        } catch (Exception e) {
            // should catch exceptions instead of throwing, because of @PostConstruct
            throw new RuntimeException(e.getMessage());
        }
    }

    @PreDestroy
    public void cleanup () {
        mongo.close();
    }

    public boolean isMasterAlive() {
        return mongo.getReplicaSetStatus().getMaster() != null;
    }

    public DataProvider<Doctor> getDoctors() {
        return doctors;
    }

    public DataProvider<Request> getRequests() {
        return requests;
    }
}