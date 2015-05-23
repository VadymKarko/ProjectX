package projectx.repository.connectors;

import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import org.junit.Test;
import projectx.domain.Request;

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
        List<Request> library = mongoConnector.select();
        assertEquals("Oleg", library.get(0).getFirstName());
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
        assertTrue(mongoConnector.replMasterStatus(mongoConnector.getMongo()));
    }

}