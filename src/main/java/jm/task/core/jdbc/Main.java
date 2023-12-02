package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Ivanov", (byte) 22);
        userService.saveUser("Petr", "Petrov", (byte) 33);
        userService.saveUser("Sidor", "Sidorov", (byte) 44);
        userService.saveUser("Alexey", "Alexeev", (byte) 55);
        userService.removeUserById(2);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}
