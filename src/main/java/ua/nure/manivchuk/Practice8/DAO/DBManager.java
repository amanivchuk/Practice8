package ua.nure.manivchuk.Practice8.DAO;

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

    public abstract void insertUser(User petrov);

    public abstract List<User> findAllUsers();
}
