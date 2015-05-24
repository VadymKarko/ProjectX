package projectx.repository.dao;

import org.mongodb.morphia.Datastore;
import projectx.domain.DomainItem;

import java.util.List;

/**
 * @author proger
 * @since 5/24/15
 */
public class DataProvider<T extends DomainItem> {
    private Class<T> clazz;
    private Datastore ds;

    public DataProvider(Class<T> clazz, Datastore ds) {
        this.ds = ds;
        this.clazz = clazz;
    }

    public List<T> find() {
        return ds.find(clazz).asList();
    }

    public void insert(T item) {
        ds.save(item);
    }

    public void update(T item) {
        ds.merge(item);
    }

    public void delete(T item) {
        ds.delete(item);
    }

    public void drop() {
        ds.getCollection(clazz).drop();
    }
}
