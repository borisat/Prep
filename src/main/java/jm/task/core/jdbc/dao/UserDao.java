package jm.task.core.jdbc.dao;

import java.sql.SQLException;
import java.util.List;
import jm.task.core.jdbc.model.User;

public interface UserDao {
    void createUsersTable() throws SQLException;

    void dropUsersTable() throws SQLException;

    void saveUser(String name, String lastName, byte age) throws SQLException;

    void removeUserById(long id) throws SQLException;

    List<User> getAllUsers();

    void cleanUsersTable() throws SQLException;
}
