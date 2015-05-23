package projectx.repository.connectors;

import com.mongodb.*;
import projectx.domain.Doctor;
import projectx.domain.Request;
import projectx.repository.wrappers.DoctorWrapper;
import projectx.repository.wrappers.RequestWrapper;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * This is an example of repository
 * WARNING!: THIS IS TUTORIAL
 *
 * @author vadym
 * @since 5/17/15
 */
@Singleton
public class MongoConnector {
    private MongoClient mongo;
    private DB dbRequests;
    private DB dbDoctors;
    private DBCollection collectionRequests;
    private DBCollection collectionDoctors;
    private ReplicaSetStatus replicaSetStatus;
    @PostConstruct
    public void init() {
        try {
            ArrayList<ServerAddress> addressList = new ArrayList<ServerAddress>();
            addServerAddress(addressList, new ServerAddress("proger-vm", (int) 27018));
            addServerAddress(addressList, new ServerAddress("proger-vm", (int) 27019));
            addServerAddress(addressList, new ServerAddress("proger-vm", (int) 27020));
            mongo = new MongoClient(addressList);
            mongo.setReadPreference(ReadPreference.secondary());
            dbRequests = mongo.getDB("requests");
            dbDoctors = mongo.getDB("doctors");
            collectionRequests = dbRequests.getCollection("requests");
            collectionDoctors = dbRequests.getCollection("doctors");
            if (collectionRequests  == null) {
                    collectionRequests  = dbRequests.createCollection("requests", null);
                }
            if (collectionDoctors  == null){
                    collectionDoctors  = dbDoctors.createCollection("requests", null);
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

    public void insertRequest(final Request request) {
        collectionRequests.insert(RequestWrapper.wrap(request));
    }

    public void insertDoctor(final Doctor doctor) {
        collectionDoctors.insert(DoctorWrapper.wrap(doctor));
    }

    public void addServerAddress(ArrayList<ServerAddress> addressList, ServerAddress address){
        addressList.add(address);
    }

    public boolean replMasterStatus(MongoClient mongo){
        replicaSetStatus = mongo.getReplicaSetStatus();
        return replicaSetStatus.getMaster() != null;
    }


    public MongoClient getMongo(){
        return this.mongo;
    }


    public ArrayList<Request> selectRequests() {
        final ArrayList<Request> requests = new ArrayList<Request>();
        final DBCursor cursor = collectionRequests.find();
        Request request;
        while (cursor.hasNext()) {
            request = RequestWrapper.unwrap(cursor.next());
            requests.add(request);
        }
        return requests;
    }

    public ArrayList<Doctor> selectDoctors() {
        final ArrayList<Doctor> doctors = new ArrayList<Doctor>();
        final DBCursor cursor = collectionDoctors.find();
        Doctor doctor;
        while (cursor.hasNext()) {
            doctor = DoctorWrapper.unwrap(cursor.next());
            doctors.add(doctor);
        }
        return doctors;
    }
}
