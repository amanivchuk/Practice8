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
        try {
            String sql = "INSERT INTO practice8.users (login) VALUES ('" + user.getLogin() + "');";
            connection = getConnection();
            stmt = connection.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }
    }


    public List<User> findAllUsers() {
        String sql = "SELECT * FROM practice8.users";
        List<User> userList = new ArrayList<User>();
        try{
            connection = getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                userList.add(extractorUser(rs));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }
        return userList;
    }

    @Override
    public void insertGroup(Group group) {
           String sql = "INSERT INTO practice8.groups (name) VALUE ('" + group.getName() +"');";
        try {
           connection = getConnection();
           stmt = connection.createStatement();
           stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }
    }

    @Override
    public List<Group> findAllGroups() {
        String sql = "SELECT * FROM practice8.groups";
        List<Group> groupList = new ArrayList<>();

        try{
            connection = getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                groupList.add(extractorGroup(rs));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }
        return groupList;
    }

    public User getUser(int id){
        User user = null;
        String sql = "SELECT * FROM practice8.users where id=?;";
        try{
            connection = getConnection();
            pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                user = User.createUser(rs.getString(2));
                user.setId(rs.getInt(1));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }
        return user;
    }

    public User getUser(String login){
        String sql = "SELECT * FROM practice8.users where login=?;";
        User user = null;
        try{
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, login);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                user = User.createUser(rs.getString(2));
                user.setId(rs.getInt(1));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }
        return user;
    }

    public Group getGroup(int id){
        String sql = "SELECT * FROM practice8.groups where id=?;";
        Group group = null;
        try{
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                group = Group.createGroup(rs.getString(2));
                group.setId(rs.getInt(1));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }
        return group;
    }

    public Group getGroup(String name){
        String sql = "SELECT * FROM practice8.groups where name=?;";
        Group group = null;
        try {
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, name);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                group = Group.createGroup(rs.getString(2));
                group.setId(rs.getInt(1));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }
        return group;
    }

    public boolean setGroupsForUser(User user, Group... groups){
        boolean res = false;
        String sql = "INSERT INTO practice8.users_groups VALUE (?,?);";
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            for(Group g : groups){
                pstm = connection.prepareStatement(sql);
                pstm.setInt(1, user.getId());
                pstm.setInt(2, g.getId());
                pstm.executeUpdate();
            }
            connection.commit();
            res = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }
        return res;
    }

    public List<String> getUserGroups(User user){
        List<String> res = new ArrayList<>();
        String sql = "SELECT group_id FROM practice8.users_groups WHERE user_id=?;";
        try {
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, user.getId());
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                Group group = getGroup(rs.getInt(1));
                res.add(group.getName());
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }
        return res;
    }

    public void deleteGroup(Group team){
        String sql = "DELETE FROM practice8.groups WHERE id='"+ team.getId() +"';";
        try{
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
//            pstm.setInt(1, team.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
           closeConnection();
        }
    }

    public void updateGroup(Group group){
        String sql = "UPDATE practice8.groups SET name=? WHERE id=?;";
        try{
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, group.getName());
            pstm.setInt(2, group.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }
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

    private void closeConnection() {
        if(pstm != null){
            try {
                pstm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
