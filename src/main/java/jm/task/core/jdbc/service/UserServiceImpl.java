package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import org.hibernate.Session;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl userService;
    private SessionFactory sessionFactory;
    private UserDaoHibernateImpl userDaoHibernate;


    public UserServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UserServiceImpl() {

    }

    public static UserServiceImpl getUserService() {
        if (userService == null) {
            userService = new UserServiceImpl(Util.getSessionFactory());
        }
        return userService;
    }

    UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

    private UserDaoHibernateImpl getUserDao() {
        if (userDaoHibernate == null) {
            userDaoHibernate = new UserDaoHibernateImpl(sessionFactory.openSession());
      } else userDaoHibernate.setSession(sessionFactory.openSession());
        return userDaoHibernate;
    }

    public void createUsersTable() {
        getUserDao().createUsersTable();

    }

    public void dropUsersTable() {
        getUserDao().dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) {
       getUserDao().saveUser(name, lastName, age);

    }

    public void removeUserById(long id) {
        getUserDao().removeUserById(id);

    }

    public List<User> getAllUsers() {
        return getUserDao().getAllUsers();
    }

    public void cleanUsersTable() {
        getUserDao().cleanUsersTable();

    }
}
