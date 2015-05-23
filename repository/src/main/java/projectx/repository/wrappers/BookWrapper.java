package projectx.repository.wrappers;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import projectx.domain.Book;

/**
 * Provides converting from MongoDB objects to objects from our domain and vice versa.
 * WARNING!: THIS IS TUTORIAL
 *
 * @author vadym
 * @since 5/17/15
 */
public class BookWrapper {

    public static DBObject wrap(final Book book) {
        final BasicDBObject document = new BasicDBObject();
        document.put("title", book.getTitle());
        document.put("author", book.getAuthor());
        document.put("pages", book.getPages());

        return document;
    }

    public static Book unwrap(final DBObject document) {
        final Book book = new Book();

        book.setTitle((String) document.get("title"));
        book.setAuthor((String) document.get("author"));
        book.setPages(Integer.valueOf(document.get("pages").toString()));

        return book;
    }
}
