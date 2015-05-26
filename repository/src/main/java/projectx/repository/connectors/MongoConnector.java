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
 * MongoConnector class provides access to MongoDB replica set
 * and provides basic operations with domain objects ({@link Doctor}, {@link Request}, ...)
 *
 * @author vadym
 * @author vladimir
 * @author oleg
 * @since 5/17/15
 */
@Singleton
@Startup
public class MongoConnector {
    /**
     * Host for MongoDB replica set instances
     */
    public static final String HOST_NAME = "localhost";
    /**
     * MongoDb database name
     */
    public static final String DATABASE_NAME = "hospital";

    /**
     * MongoDB native client
     */
    private MongoClient mongo;

    /**
     * Data access to Doctors collection
     */
    private DataProvider<Doctor> doctors;
    /**
     * Data access to Requests collection
     */
    private DataProvider<Request> requests;


    /**
     * Initialise MongoDB client
     */
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

    /**
     * Close MongoDB client
     */
    @PreDestroy
    public void cleanup () {
        mongo.close();
    }

    /**
     * Checks if MongoDB primary node is available
     * @return <code>true</code> if primary node is available, <code>false</code> otherwise
     */
    public boolean isMasterAlive() {
        return mongo.getReplicaSetStatus().getMaster() != null;
    }

    /**
     * Provides access to Doctors collection
     * @return Doctors collection
     */
    public DataProvider<Doctor> getDoctors() {
        return doctors;
    }

    /**
     * Provides access to Requests collection
     * @return Request collection
     */
    public DataProvider<Request> getRequests() {
        return requests;
    }
}