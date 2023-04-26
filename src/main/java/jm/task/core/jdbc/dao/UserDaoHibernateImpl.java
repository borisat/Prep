package jm.task.core.jdbc.dao;

import java.lang.module.Configuration;
import java.util.List;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserDaoHibernateImpl implements UserDao {

    Session session;

    public UserDaoHibernateImpl(Session session) {
        this.session = session;
    }


    @Override
    public void createUsersTable() {
        Util util = new Util();
    }

    @Override
    public void dropUsersTable() {
        Transaction tx1 = session.beginTransaction();
        String sql = "DROP TABLE IF EXISTS test_jdbc.public.user_hiber ";
        Query query = session.createNativeQuery(sql);
        query.executeUpdate();
        tx1.commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        this.session.save(new User(name, lastName, age));
        System.out.println("User с именем " + name + " добавлен через Hiber");
    }

    @Override
    public void removeUserById(long id) {
        User user = this.session.get(User.class, id);
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = (List<User>) this.session
                .getSessionFactory()
                .openSession()
                .createQuery("From User")
                .list();

        for (User user : users) {
            System.out.println("Hiber" + user.toString());
        }
        return users;

    }

    @Override
    public void cleanUsersTable() {
        Transaction tx1 = session.beginTransaction();
        String hql = "DELETE FROM User";
        Query query = session.createQuery(hql);
        query.executeUpdate();
        tx1.commit();
    }
}
