package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDaoJDBCImpl userDaoJDBC;
//    UserDaoHibernateImpl userDaoHibernate;

    Util util = new Util();

    public UserServiceImpl() {
        this.userDaoJDBC = new UserDaoJDBCImpl();
//        this.userDaoHibernate = new UserDaoHibernateImpl();
    }

    public void createUsersTable() {
        userDaoJDBC.createUsersTable();

        Session session = util.getSessionFactory().openSession();
        UserDaoHibernateImpl dao = new UserDaoHibernateImpl(session);
        dao.createUsersTable();
        session.close();
    }

    public void dropUsersTable() {
        userDaoJDBC.dropUsersTable();

        Session session = util.getSessionFactory().openSession();
        UserDaoHibernateImpl dao = new UserDaoHibernateImpl(session);
        dao.dropUsersTable();
        session.close();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoJDBC.saveUser(name, lastName, age);

        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        UserDaoHibernateImpl dao = new UserDaoHibernateImpl(session);
        dao.saveUser(name, lastName, age);
        transaction.commit();
        session.close();
    }

    public void removeUserById(long id) {
        userDaoJDBC.removeUserById(id);

        Session session = util.getSessionFactory().openSession();
        UserDaoHibernateImpl dao = new UserDaoHibernateImpl(session);
        dao.removeUserById(id);
        session.close();
    }

    public List<User> getAllUsers() {




        List<User> jdbcuser = userDaoJDBC.getAllUsers();

        Session session = util.getSessionFactory().openSession();
        UserDaoHibernateImpl dao = new UserDaoHibernateImpl(session);
        List<User> user = dao.getAllUsers();
        session.close();
        return user;
    }

    public void cleanUsersTable() {
        userDaoJDBC.cleanUsersTable();

        Session session = util.getSessionFactory().openSession();
        UserDaoHibernateImpl dao = new UserDaoHibernateImpl(session);
        dao.cleanUsersTable();
        session.close();

    }
}
