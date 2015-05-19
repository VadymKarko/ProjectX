package projectx.repository.connectors;

import com.mongodb.*;
import projectx.domain.Book;
import projectx.repository.wrappers.BookWrapper;

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
    private Mongo mongo;
    private DB db;
    private DBCollection collection;

    @PostConstruct
    public void init() {
        try {
            List addressList = new ArrayList();
            addServerAddress(addressList, new ServerAddress("127.0.0.1",27018));
            addServerAddress(addressList, new ServerAddress("127.0.0.1",27019));
            addServerAddress(addressList, new ServerAddress("127.0.0.1",270120));
            mongo = new Mongo(addressList);
            mongo.slaveOk();
            db = mongo.getDB("books");
            collection = db.getCollection("books");
            if (collection == null) {
                collection = db.createCollection("books", null);
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void insert(final Book book) {
        collection.insert(BookWrapper.wrap(book));
    }

    public void addServerAddress(List<ServerAddress> addressList, ServerAddress address){
        addressList.add(address);
    }

    public List<Book> select() {
        final List<Book> library = new ArrayList<Book>();
        final DBCursor cursor = collection.find();
        Book book;

        while (cursor.hasNext()) {
            book = BookWrapper.unwrap(cursor.next());
            library.add(book);
        }

        return library;
    }
}
