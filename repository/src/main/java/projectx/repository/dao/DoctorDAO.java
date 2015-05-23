package projectx.repository.dao;

import com.mongodb.MongoClient;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import projectx.domain.Doctor;
import projectx.repository.Constants;
import projectx.repository.connectors.MongoConnector;

import java.util.List;

/**
 * @author proger
 * @since 5/23/15
 */

public class DoctorDAO extends BasicDAO<Doctor,ObjectId> {


    public DoctorDAO(MongoClient mongoClient, Morphia morphia, String dbName) {
        super(mongoClient, morphia, dbName);
    }

    public DoctorDAO(){
        super(MongoConnector.getMongo(),MongoConnector.getMorphia(), Constants.DATABASE_NAME);
    }


    public List<Doctor> findAllDoctors(){
        return getDs().find(Doctor.class).asList();
    }

    public void saveDoctor(Doctor doctor){
        getDs().save(doctor);
    }
    public void updateDoctor( Doctor doctor ){
        getDs().merge( doctor );
    }

    public void removeDoctor( Doctor doctor ){
        getDs().delete( doctor );
    }

}
