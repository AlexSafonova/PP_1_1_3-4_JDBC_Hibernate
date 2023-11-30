package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    UserDao userDaoHibernate = new UserDaoHibernateImpl();
    private static final Session session = Util.getSessionFactory().openSession();
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction = session.beginTransaction();
        String sql = "CREATE TABLE IF NOT EXISTS Users (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), lastName VARCHAR(255), age TINYINT)";
        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS Users";
        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        session.close();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO Users (name, lastName, age) VALUES (:name, :lastName, :age)";
        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM Users WHERE id = :id";
        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        session.close();

    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = session.createQuery("FROM User").list();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "DELETE FROM Users";
        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        session.close();
    }
}
