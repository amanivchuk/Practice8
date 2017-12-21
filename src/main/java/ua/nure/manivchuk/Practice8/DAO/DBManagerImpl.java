package ua.nure.manivchuk.Practice8.DAO;

import ua.nure.manivchuk.Practice8.beans.Group;
import ua.nure.manivchuk.Practice8.beans.User;
import ua.nure.manivchuk.Practice8.utils.Constance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nec on 21.12.17.
 */
public class DBManagerImpl extends DBManager{
    private Statement stmt = null;

    public static Connection getConnection(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(Constance.DB_URL, Constance.LOGIN, Constance.PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void insertUser(User user){
        Connection connection = null;
        try {
            String sql = "INSERT INTO practice8.users (login) VALUES ('" + user.getLogin() + "');";
            connection = getConnection();
            stmt = connection.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> findAllUsers() {
        String sql = "SELECT * FROM practice8.users";
        List<User> userList = new ArrayList<User>();
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try{
            connection = getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                userList.add(extractorUser(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return userList;
    }

    @Override
    public void insertGroup(Group group) {
           Connection connection = null;
           String sql = "INSERT INTO practice8.groups (name) VALUE ('" + group.getName() +"');";
        try {
           connection = getConnection();
           stmt = connection.createStatement();
           stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Group> findAllGroups() {
        String sql = "SELECT * FROM practice8.groups";
        List<Group> groupList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try{
            connection = getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                groupList.add(extractorGroup(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return groupList;
    }

    private Group extractorGroup(ResultSet rs) {
        Group group = new Group();
        try {
            group.setId(rs.getInt("id"));
            group.setName(rs.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group;
    }


    private User extractorUser(ResultSet rs) {
        User user = new User();
        try {
            user.setId(rs.getInt("id"));
            user.setLogin(rs.getString("login"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
