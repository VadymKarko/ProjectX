package projectx.repository.connectors;

import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import projectx.domain.Request;
import projectx.repository.Constants;
import projectx.repository.dao.RequestDAO;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author proger
 * @since 5/23/15
 */
public class MongoConnectorTest {

    @Test
    public void testInit() throws UnknownHostException, MongoException {
    MongoConnector mongoConnector = new MongoConnector();
        mongoConnector.init();
      //  List<Request> library = mongoConnector.selectRequests();
      //  assertEquals("Oleg", library.get(0).getFirstName());
    }

    @Test
    public void testaddServerAddress() throws IllegalArgumentException,UnknownHostException {
        MongoConnector mongoConnector = new MongoConnector();
        ArrayList<ServerAddress> testAddressList = new ArrayList<ServerAddress>();
        mongoConnector.addServerAddress(testAddressList,new ServerAddress("proger-vm",27018));
        ArrayList<ServerAddress> addressList = new ArrayList<ServerAddress>();
        addressList.add(new ServerAddress("proger-vm",27018));
        assertEquals(testAddressList, addressList);
    }

    @Test
    public void testReplMasterStatus() throws MongoException {
        MongoConnector mongoConnector = new MongoConnector();
        mongoConnector.init();
        assertTrue(mongoConnector.replMasterStatus(MongoConnector.getMongo()));
    }
    @Test
    public void testInsert(){
        MongoConnector mongoConnector = new MongoConnector();
        mongoConnector.init();
        Datastore ds = MongoConnector.getDatastore();
        for (int i=0;i<10;i++){
            ds.save(new Request("vladimir"+String.valueOf(i),"mishin","razdolbaystvo","len\'",String.valueOf(i)+"0123"));
        }
        List<Request> list= ds.find(Request.class).asList();
        Request result = ds.find(Request.class).get();
        System.out.println(result);
        System.out.println(list);
    }
    @Test
    public void testSelectRequest() throws MongoException {
        MongoConnector mongoConnector = new MongoConnector();
        mongoConnector.init();
        RequestDAO requestDAO = new RequestDAO(MongoConnector.getMongo(),MongoConnector.getMorphia(), Constants.DATABASE_NAME);
        List<Request> requestList = requestDAO.findAllRequests();
        System.out.println(requestList);
    }
/*
   @Test
    public void testSelectDoctors() throws MongoException {
        MongoConnector mongoConnector = new MongoConnector();
        mongoConnector.init();
        List<Doctor> doctors = mongoConnector.selectDoctors();
        assertEquals("Oleg", doctors.get(0).getFirstName());
    }*/

//    @Test
//    public void testSelectDoctors() throws MongoException {
//        MongoConnector mongoConnector = new MongoConnector();
//        mongoConnector.init();
//        Morphia morphia = new Morphia();
//        DoctorDAO doctorDAO = new DoctorDAO(mongoConnector.getMongo(),morphia,"doctors");
//        List<Doctor> doctorsList = doctorDAO.find().asList();
//
//       List<Doctor> doctors = mongoConnector.selectDoctors();
//      //  assertEquals("Oleg", doctors.get(0).getFirstName());
//    }

    @Test
    public void testInsertRequests() throws MongoException {

    }

    @Test
    public void testInsertDoctors() throws MongoException {

    }


}