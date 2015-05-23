package projectx.repository.dao;

import com.mongodb.MongoClient;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import projectx.domain.Request;
import projectx.repository.Constants;
import projectx.repository.connectors.MongoConnector;

import java.util.List;

/**
 * @author proger
 * @since 5/23/15
 */

public class RequestDAO extends BasicDAO<Request,ObjectId> {

    public RequestDAO(MongoClient mongoClient, Morphia morphia, String dbName) {
        super(mongoClient, morphia, dbName);
    }

    public RequestDAO(){
        super(MongoConnector.getMongo(),MongoConnector.getMorphia(), Constants.DATABASE_NAME);
    }


    public List<Request> findAllRequests(){
        return getDs().find(Request.class).asList();
    }

    public void saveRequest(Request request){
         getDs().save(request);
    }
    public void updateRequest( Request request ){
        getDs().merge(request);
    }

    public void removeRequest( Request request ){
        getDs().delete(request);
    }
}