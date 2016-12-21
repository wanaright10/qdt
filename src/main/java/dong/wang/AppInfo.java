package dong.wang;

/**
 * Created on 12/5/16.
 */
public class AppInfo {
    private String id;
    private String name;

    public AppInfo() {
    }

    public AppInfo(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
