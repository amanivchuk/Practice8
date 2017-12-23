package ua.nure.manivchuk.Practice8.DAO;

import ua.nure.manivchuk.Practice8.beans.Group;
import ua.nure.manivchuk.Practice8.beans.User;
import ua.nure.manivchuk.Practice8.utils.Constance;

import java.util.List;

/**
 * Created by nec on 21.12.17.
 */
public abstract class DBManager {
    private static DBManager instance;

    public static DBManager getInstance() {
        if(instance == null){
            try{
                Class.forName(Constance.DRIVER_CLASS_NAME);
                Class clazz = Class.forName(Constance.DAO_FACTORY_CLASS_NAME);
                instance = (DBManager) clazz.newInstance();

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public abstract void insertUser(User user);

    public abstract List<User> findAllUsers();

    public abstract void insertGroup(Group group);

    public abstract List<Group> findAllGroups();

    public abstract User getUser(int id);

    public abstract User getUser(String login);

    public abstract Group getGroup(int id);

    public abstract Group getGroup(String name);

    public abstract boolean setGroupsForUser(User user, Group... groups);

    public abstract List<String> getUserGroups(User user);

    public abstract void updateGroup(Group group);

    public abstract void deleteGroup(Group team);
}
