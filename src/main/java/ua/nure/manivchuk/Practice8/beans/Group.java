package ua.nure.manivchuk.Practice8.beans;

/**
 * Created by nec on 21.12.17.
 */
public class Group {
    private int id;
    private String name;

    public Group() {
    }

    public Group(String name) {
        this.name = name;
    }

    public Group(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }

    public static Group createGroup(String name) {
        return new Group(name);
    }
}
