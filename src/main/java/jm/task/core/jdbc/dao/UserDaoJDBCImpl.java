package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Util util = Util.getUtil();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            PreparedStatement pst = util.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS users" +
                    "(id int auto_increment not null key ,name VARCHAR(40) not null " +
                    ", lastName varchar(50)not null, " + " age int not null)");
            pst.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try {
            PreparedStatement pst = util.getConnection()
                    .prepareStatement("drop table if exists users");
            pst.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement pst = util.getConnection()
                    .prepareStatement("insert into users ( name,lastName,age) " +
                            "VALUES (?,?,?) ");
            pst.setString(1, name);
            pst.setString(2, lastName);
            pst.setByte(3, age);
            pst.execute();
            System.out.println("User именем - " + name + " добавлен в базу данных");
            User user = new User();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try {
            PreparedStatement pst = util.getConnection()
                    .prepareStatement("delete from users where id = (?)");
            pst.setLong(1, id);
            pst.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        try {
            Statement statement = util.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from users");
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                byte age = resultSet.getByte(4);
                User user = new User(id, name, lastName, age);
                userList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(userList);
        return userList;
    }

    public void cleanUsersTable() {
        try {
            PreparedStatement pst = util.getConnection().prepareStatement("delete from users");
            pst.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}

