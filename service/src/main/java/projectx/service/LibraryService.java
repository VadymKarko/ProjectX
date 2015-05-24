package projectx.service;

import projectx.domain.Book;
import projectx.repository.connectors.MongoConnector;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Service that provides library operations.
 * WARNING!: THIS IS TUTORIAL
 *
 * @author vadym
 * @since 5/17/15
 */
@Deprecated
@Stateless
public class LibraryService {
    @EJB
    private MongoConnector connector;
    private List<Book> library;

    @PostConstruct
    private void init() {
        //library = connector.select();
    }

    public void addBook(Book book) {
        library.add(book);
        //connector.insert(book);
    }

    public List<Book> getLibrary() {
        return library;
    }
}
