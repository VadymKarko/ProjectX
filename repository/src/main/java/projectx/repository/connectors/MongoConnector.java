package projectx.repository.connectors;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
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
            mongo = new Mongo();
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
