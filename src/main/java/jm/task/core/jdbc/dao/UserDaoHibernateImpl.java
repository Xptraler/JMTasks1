package jm.task.core.jdbc.dao;

import org.hibernate.*;
import jm.task.core.jdbc.model.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Session session;
    private SessionFactory sessionFactory;


    public UserDaoHibernateImpl(Session session) {
        this.session = session;
    }


    @Override
    public void createUsersTable() {
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS user" +
                    "(id int auto_increment not null key ,name VARCHAR(40) not null " +
                    ", lastName varchar(50)not null, " + " age int not null)");
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = session.beginTransaction();
        try {
            session.createSQLQuery("drop table if exists user").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = session.beginTransaction();
        try {
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
            System.out.println("User именем - " + name + " добавлен в базу данных");
        } catch (Exception e) {
            transaction.rollback();

        } finally {
            session.close();
        }
    }


    @Override
    public void removeUserById(long id) {
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("DELETE FROM User where id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = session.beginTransaction();
        List<User> list = new ArrayList<>();
        try {
            list = session.createQuery("FROM User").list();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("DELETE User");
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }

    }

    public void setSession(Session session) {
        this.session = session;
    }
}

