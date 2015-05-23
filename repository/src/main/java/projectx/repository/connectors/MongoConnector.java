package projectx.repository.connectors;

import com.mongodb.*;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import projectx.domain.Request;
import projectx.repository.Constants;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is an example of repository
 * WARNING!: THIS IS TUTORIAL
 *
 * @author vadym
 * @since 5/17/15
 */
@Singleton
public class MongoConnector {
    private static MongoClient mongo;
    private static DB dbHospital;
    private static Morphia morphia;
    private static Datastore datastore;

    private DBCollection collection;


    private ReplicaSetStatus replicaSetStatus;

    @PostConstruct
    public void init() {
        try {
            List<ServerAddress> addressList = new ArrayList<ServerAddress>();
            addServerAddress(addressList, new ServerAddress(Constants.HOST_NAME,  27018));
            addServerAddress(addressList, new ServerAddress(Constants.HOST_NAME,  27019));
            addServerAddress(addressList, new ServerAddress(Constants.HOST_NAME,  27020));
            mongo = new MongoClient(addressList);
            mongo.setReadPreference(ReadPreference.secondary());
            morphia = new Morphia();

            datastore = morphia.createDatastore(mongo, Constants.DATABASE_NAME);
            morphia.map(Request.class);
            dbHospital = mongo.getDB(Constants.DATABASE_NAME);
            if (dbHospital.getCollection(Constants.REQUESTS) == null) {
                dbHospital.createCollection(Constants.REQUESTS, null);
            }
            if (dbHospital.getCollection(Constants.DOCTORS) == null) {
                dbHospital.createCollection(Constants.DOCTORS, null);
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e.getMessage());
        } catch (MongoException.Network e) {
            throw new RuntimeException(e.getMessage());
        } catch (MongoException e) {
            throw new RuntimeException(e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void addServerAddress(List<ServerAddress> addressList, ServerAddress address) {
        addressList.add(address);
    }

    public boolean replMasterStatus(MongoClient mongo) {
        replicaSetStatus = mongo.getReplicaSetStatus();
        return replicaSetStatus.getMaster() != null;
    }


    public static MongoClient getMongo() {
        return mongo;
    }

    public static Morphia getMorphia() {
        return morphia;
    }

    public static Datastore getDatastore() {
        return datastore;
    }


}