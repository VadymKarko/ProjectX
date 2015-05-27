package projectx.repository.dao;

import org.mongodb.morphia.Datastore;
import projectx.domain.DomainItem;

import java.util.List;

/**
 * DataProvider is a generic class that provides basic
 * operations on domain objects in MongoDB.
 *
 * @author vadym
 * @author vladimir
 * @since 5/24/15
 */
public class DataProvider<T extends DomainItem> {
    /**
     * Class of a generic type
     */
    private Class<T> clazz;
    /**
     * Morphia MongoDB collection associated with domain object
     */
    private Datastore ds;

    /**
     * Constructor
     *
     * @param clazz class of any domain object that have to be stored in MongoDB
     * @param ds Morphia MongoDB collection associated with this domain object
     */
    public DataProvider(Class<T> clazz, Datastore ds) {
        this.ds = ds;
        this.clazz = clazz;
    }

    /**
     * Selects all entries in collection
     *
     * @return a list of entries
     */
    public List<T> find() {
        return ds.find(clazz).asList();
    }


    /**
     * Selects entries by criteria
     *
     * @param field criteria field
     * @param value value of criteria
     * @return a list of entries
     */
    public List<T> find(String field, Object value) {
        return ds.createQuery(clazz).filter(field, value).asList();
    }

    public T findOne(String field, Object value) {
        return ds.createQuery(clazz).filter(field, value).get();
    }

    /**
     * ToDo:
     * @param item
     */
    public void insert(T item) {
        ds.save(item);
    }

    /**
     * ToDo:
     * @param item
     */
    public void update(T item) {
        ds.merge(item);
    }

    /**
     * ToDo:
     * @param item
     */
    public void delete(T item) {
        ds.delete(item);
    }

    /**
     * ToDo:
     */
    public void drop() {
        ds.getCollection(clazz).drop();
    }
}
