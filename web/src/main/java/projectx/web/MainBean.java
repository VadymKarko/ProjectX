package projectx.web;

import projectx.domain.Book;
import projectx.service.LibraryService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.List;

/**
 * JSF Managed Bean. Stores information from the web page.
 * Invokes service layer.
 * WARNING!: THIS IS TUTORIAL
 *
 * @author vadym
 * @since 5/16/15
 */
@ManagedBean
public class MainBean implements Serializable {
    @EJB
    private LibraryService service;
    private Book book = new Book();

    public void setBook(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public void add() {
        service.addBook(book);
        book = new Book();
    }

    public List<Book> load() {
        return service.getLibrary();
    }
}
