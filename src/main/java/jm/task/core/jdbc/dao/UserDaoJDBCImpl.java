package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection = Util.getUtil().getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable()  {
        try {
            connection.setAutoCommit(false);
            PreparedStatement pst = connection.prepareStatement("CREATE TABLE IF NOT EXISTS users" +
                    "(id int auto_increment not null key ,name VARCHAR(40) not null " +
                    ", lastName varchar(50)not null, " + " age int not null)");
            pst.execute();
            connection.commit();
            pst.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();

        }
    }

    public void dropUsersTable() {
        try {
            connection.setAutoCommit(false);
            PreparedStatement pst = connection
                    .prepareStatement("drop table if exists users");
            pst.execute();
            connection.commit();
            pst.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age)  {
        try {
            connection.setAutoCommit(false);
            PreparedStatement pst = connection
                    .prepareStatement("insert into users ( name,lastName,age) " +
                            "VALUES (?,?,?) ");
            pst.setString(1, name);
            pst.setString(2, lastName);
            pst.setByte(3, age);
            pst.execute();
            System.out.println("User именем - " + name + " добавлен в базу данных");
            User user = new User();
            connection.commit();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement pst = connection
                    .prepareStatement("delete from users where id = (?)");
            pst.setLong(1, id);
            pst.execute();
            connection.commit();
            pst.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from users");
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                byte age = resultSet.getByte(4);
                User user = new User(id, name, lastName, age);
                userList.add(user);
                connection.commit();
            }
            resultSet.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println(userList);
        return userList;
    }

    public void cleanUsersTable() {
        try {
            connection.setAutoCommit(false);
            PreparedStatement pst = connection.prepareStatement("delete from users");
            pst.execute();
            connection.commit();
            pst.close();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}

