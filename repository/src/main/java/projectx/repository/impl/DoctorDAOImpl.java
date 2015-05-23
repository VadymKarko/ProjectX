package projectx.repository.impl;

import com.mongodb.MongoClient;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import projectx.domain.Doctor;
import projectx.repository.Constants;
import projectx.repository.connectors.MongoConnector;

import javax.ejb.Stateless;
import java.util.List;

/**
 * @author proger
 * @since 5/23/15
 */
@Stateless
public class DoctorDAOImpl extends BasicDAO<Doctor,ObjectId> {


    public DoctorDAOImpl(MongoClient mongoClient, Morphia morphia, String dbName) {
        super(mongoClient, morphia, dbName);
    }

    public DoctorDAOImpl(){
        super(MongoConnector.getMongo(),MongoConnector.getMorphia(), Constants.DATABASE_NAME);
    }


    public List<Doctor> findAllDoctors(){
        return getDs().find(Doctor.class).asList();
    }

    public void saveRequest(Doctor doctor){
        getDs().save(doctor);
    }
    public void updateRequest( Doctor doctor ){
        getDs().merge( doctor );
    }

    public void removeRequest( Doctor doctor ){
        getDs().delete( doctor );
    }

}
