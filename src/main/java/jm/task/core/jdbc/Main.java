package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        Util util = Util.getUtil();
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Tom", "Tom", (byte) 33);
        userService.saveUser("Art", "Art", (byte) 29);
        userService.saveUser("Jan", "Jan", (byte) 32);
        userService.saveUser("Max", "Max", (byte) 25);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}
