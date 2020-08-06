package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl.getUserService().createUsersTable();
        UserServiceImpl.getUserService().saveUser("Tom", "Tom", (byte) 33);
        UserServiceImpl.getUserService().saveUser("Art", "Art", (byte) 29);
        UserServiceImpl.getUserService().saveUser("Jan", "Jan", (byte) 32);
        UserServiceImpl.getUserService().saveUser("Max", "Max", (byte) 25);
        UserServiceImpl.getUserService().getAllUsers();
        UserServiceImpl.getUserService().cleanUsersTable();
        //UserServiceImpl.getUserService().dropUsersTable();


    }
}
