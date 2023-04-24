package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    Connection connection;



    public UserDaoJDBCImpl(Connection connection) {
        this.connection = Util.getPostrgresConnection();
    }

    public void createUsersTable() {

    }

    public void dropUsersTable() {

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Util util = new Util();

        util.execUpdate("INSERT INTO test_jdbc.public.user (Name, Lastname, Age) values " +
                "('" + name + "', '" + lastName + "', " + age + ")");


    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        String SQL_SELECT = "Select * from test_jdbc.public.user";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT);
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
                System.out.println("User {id: " + user.getId() + "," +
                        " name: " + user.getName() +
                        " lastname " + user.getLastName() +
                        ", lastname: " + user.getAge() + "}");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public void cleanUsersTable() {

    }
}
