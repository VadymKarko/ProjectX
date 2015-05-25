package projectx.service;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;

/**
 * @author proger
 * @since 5/24/15
 */
@Singleton
public class Cache {
    private HazelcastInstance hazelcast;

    @PostConstruct
    public void init() {
        hazelcast = Hazelcast.newHazelcastInstance();
    }

    @PreDestroy
    public void cleanup() {
        hazelcast.shutdown();
    }

    public HazelcastInstance getHazelcast() {
        return hazelcast;
    }
}
