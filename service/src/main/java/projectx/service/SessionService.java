package projectx.service;

import com.hazelcast.core.Member;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.*;

/**
 * @author proger
 * @since 5/24/15
 */
@Stateless
public class SessionService {
    @EJB
    private Cache cache;
    private Map<String, List<String>> map;
    private List<String> values;

    @PostConstruct
    public void init() {
        map = cache.getHazelcast().getMap("cache");
        values = map.get("message");

        if (values == null) values = new ArrayList<String>();
    }

    public void addMessage(String msg) {
        values.add(msg);
        map.put("message", values);
    }

    public List<String> getMessage() {
        List<String> x = map.get("message");
        return (x != null) ? x : Collections.<String>emptyList();
    }

    public List<String> getClusterInfo() {
        List<String> list = new ArrayList<String>();
        Set<Member> members = cache.getHazelcast().getCluster().getMembers();
        for (Member member : members) {
            list.add(member.toString());
        }

        return list;
    }
}
