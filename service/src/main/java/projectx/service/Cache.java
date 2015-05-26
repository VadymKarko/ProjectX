package projectx.service;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * Singleton class that provides memory-grid storage.
 * Class is used for replicating shared data between clustered nodes via Hazelcast.
 * {@link Singleton} - is a single object for whole cluster node.
 * (i.e. every node contains its own singleton object).
 *
 * @author vadym
 * @since 5/24/15
 */
@Singleton
@Startup
public class Cache {
    /**
     * Hazelcast node instance
     */
    private HazelcastInstance hazelcast;


    /**
     * Creates a new Hazelcast node in a cluster
     */
    @PostConstruct
    public void init() {
        hazelcast = Hazelcast.newHazelcastInstance();
    }

    /**
     * Shuts down this Hazelcast node
     */
    @PreDestroy
    public void cleanup() {
        hazelcast.shutdown();
    }

    // ToDo: Not sure if all hazelcast instance is need from outside.
    // ToDo: Probably, few more methods will be encapsulated.
    public HazelcastInstance getHazelcast() {
        return hazelcast;
    }
}
