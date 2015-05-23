package projectx.repository.connectors;

import com.mongodb.*;
import projectx.domain.Book;
import projectx.domain.Request;
import projectx.repository.wrappers.BookWrapper;
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
    private DB db;
    private DBCollection collection;
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
            db = mongo.getDB("requests");
            collection = db.getCollection("requests");
            if (collection == null) {
                collection = db.createCollection("requests", null);
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

    public void insert(final Book book) {
        collection.insert(BookWrapper.wrap(book));
    }

    public void addServerAddress(ArrayList<ServerAddress> addressList, ServerAddress address){
        addressList.add(address);
    }

    public boolean replMasterStatus(MongoClient mongo){
        replicaSetStatus = mongo.getReplicaSetStatus();
        if (replicaSetStatus.getMaster() == null)
            {
                return false;
            }
        else
            {
                return true;
            }
    }

    public MongoClient getMongo(){
        return this.mongo;
    }


    public ArrayList<Request> select() {
        final ArrayList<Request> library = new ArrayList<Request>();
        final DBCursor cursor = collection.find();
        Request book;

        while (cursor.hasNext()) {
            book = RequestWrapper.unwrap(cursor.next());
            library.add(book);
        }

        return library;
    }
}
