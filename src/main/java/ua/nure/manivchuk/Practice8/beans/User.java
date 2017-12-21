package ua.nure.manivchuk.Practice8.beans;

/**
 * Created by nec on 21.12.17.
 */
public class User {
    private int id;
    private String login;

    public User() {
    }

    public User(String login) {
        this.login = login;
    }

    public User(int id, String login) {
        this.id = id;
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }

    public static User createUser(String login) {
        return new User(login);
    }
}
