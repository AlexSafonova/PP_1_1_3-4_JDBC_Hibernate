package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
   Connection connection = getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sqlCommand = "CREATE TABLE Users (Id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), lastName VARCHAR(20), age TINYINT)";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommand);
            System.out.println("Таблица создана");
        } catch (SQLException e) {
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS Users");
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Users (name, lastName, age) VALUES (?, ?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {

        }

    }

    public void removeUserById(long id) {
        try(PreparedStatement statement = connection.prepareStatement("DELETE FROM Users WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("User с id " + id + " удален");
        } catch (SQLException e) {

        }

    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {

        }
        return userList;
    }

    public void cleanUsersTable() {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE Users");
            System.out.println("Таблица очищена");
        } catch (SQLException e) {

        }
    }
}
