package projectx.web;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
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
@SessionScoped
public class MainBean implements Serializable {
    private List<String> list;
    private String value;

    @PostConstruct
    public void init() {
        list = new ArrayList<String>();
        list.add("default");
    }

    public List<String> getList() {
        return list;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void add() {
        list.add(value);
    }
}
