package jm.task.core.jdbc.dao;

import java.util.List;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserDaoHibernateImpl implements UserDao {
    Session session;
    Util util;

    public UserDaoHibernateImpl() {
        this.util = new Util();
    }


    @Override
    public void createUsersTable() {
    }

    @Override
    public void dropUsersTable() {
        util.getSessionFactory().close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        this.session = util.getSessionFactory().openSession();
        session.save(new User(name, lastName, age));
        session.close();
        System.out.println("User с именем " + name + " добавлен через Hiber");
    }

    @Override
    public void removeUserById(long id) {
        this.session = util.getSessionFactory().openSession();
        User user = session.get(User.class, id);
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        this.session = util.getSessionFactory().openSession();
        List<User> users = (List<User>) session
                .createQuery("From User")
                .list();

        for (User user : users) {
            System.out.println("Hiber" + user.toString());
        }
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        this.session = util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        String hql = "DELETE FROM User";
        Query query = session.createQuery(hql);
        query.executeUpdate();
        tx1.commit();
        session.close();
    }
}
