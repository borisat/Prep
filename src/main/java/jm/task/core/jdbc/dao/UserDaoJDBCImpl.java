package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    Util util;


    public UserDaoJDBCImpl() {
        this.util = new Util();
    }

    public void createUsersTable() {
        try {
            util.execUpdate("CREATE TABLE IF NOT EXISTS test_jdbc.public.users_JDBC " +
                    "(user_id serial PRIMARY KEY, " +
                    "name VARCHAR(256), " +
                    "lastname VARCHAR(256), " +
                    "age INTEGER)");
        } catch (SQLException e) {
            System.out.println("Ошибка при создании таблицы: " + e.getMessage());
        }
    }

    public void dropUsersTable() {
        try {
            util.execUpdate("DROP TABLE IF EXISTS test_jdbc.public.users_JDBC");
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении таблицы: " + e.getMessage());
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        try {
            util.execUpdate("INSERT INTO test_jdbc.public.users_JDBC (Name, Lastname, Age) values " +
                    "('" + name + "', '" + lastName + "', " + age + ")");
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлени юзера: " + e.getMessage());
        }

        System.out.println("User с именем " + name + " добавлен через JDBC");


    }

    public void removeUserById(long id) {

        try {
            util.execUpdate("DELETE FROM test_jdbc.public.users_JDBC WHERE user_id = " + id);
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении юзера: " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        String SQL_SELECT = "Select * from test_jdbc.public.users_JDBC";

        try {
            PreparedStatement preparedStatement = Util.getPostrgresConnection().prepareStatement(SQL_SELECT);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                long id = resultSet.getLong("user_id");
                String name = resultSet.getString("name");
                String lastname = resultSet.getString("lastname");
                byte age = resultSet.getByte("age");

                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setLastName(lastname);
                user.setAge(age);
                result.add(user);
            }

            for (User user : result) {
                System.out.println("JDBC" + user.toString());
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при получении списка юзеров: " + e.getMessage());
        }
        return result;
    }

    public void cleanUsersTable() {

        try {
            util.execUpdate("DELETE FROM test_jdbc.public.users_JDBC");
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении юзера: " + e.getMessage());
        }

    }
}
