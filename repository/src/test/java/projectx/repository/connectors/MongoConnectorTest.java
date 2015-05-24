package projectx.repository.connectors;

import com.mongodb.MongoException;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import projectx.domain.Request;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author proger
 * @since 5/23/15
 */
public class MongoConnectorTest {
    private MongoConnector connector;

    @Before
    public void setUp() throws Exception {
        connector = new MongoConnector();
        connector.init();
    }

    @After
    public void tearDown() throws Exception {
        connector.getRequests().drop();
        connector.getDoctors().drop();
        connector.cleanup();
    }


    @Test
    public void shouldInit() throws Exception {
        throw new UnsupportedOperationException("Not implemented yet");
    }


    @Test
    public void shouldInsertAndSelectRequest() {
        final Request expected = new Request(
                "Vladimir",
                "Mishin",
                "extra high working productivity",
                "happiness, success, profit",
                "+0(123)456-78-90",
                new Date()
        );

        connector.getRequests().insert(expected);
        List<Request> list = connector.getRequests().find();

        System.out.println(list);

        assertEquals(expected.getId(), list.get(0).getId());
    }


    @Test
    public void shouldSelectDoctors() throws MongoException {
        throw new UnsupportedOperationException("Not implemented yet");
    }


    @Ignore
    @Test
    public void shouldInsertRequests() throws Exception {
        throw new UnsupportedOperationException("Not implemented yet");
    }


    @Ignore
    @Test
    public void shouldInsertDoctors() throws Exception {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}