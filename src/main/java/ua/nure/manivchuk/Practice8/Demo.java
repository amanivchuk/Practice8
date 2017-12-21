package ua.nure.manivchuk.Practice8;

import ua.nure.manivchuk.Practice8.DAO.DBManager;
import ua.nure.manivchuk.Practice8.beans.Group;
import ua.nure.manivchuk.Practice8.beans.User;

import java.util.List;

/**
 * Created by nec on 21.12.17.
 */
public class Demo {
    private static <T> void printList(List<T> list) {
        for (T element : list) {
            System.out.println(element);
        }
    }

    public static void main(String[] args) {
// users ==> [ivanov]; groups ==> [teamA]

        DBManager dbManager = DBManager.getInstance();

// Part 1
//        dbManager.insertUser(User.createUser("petrov"));
//        dbManager.insertUser(User.createUser("obama"));
        printList(dbManager.findAllUsers());
// users ==> [ivanov, petrov, obama]

        System.out.println("===========================");


        // Part 2
//        dbManager.insertGroup(Group.createGroup("teamB"));
//        dbManager.insertGroup(Group.createGroup("teamC"));
        printList(dbManager.findAllGroups());
        // groups ==> [teamA, teamB, teamC]

        System.out.println("===========================");
    }
}
