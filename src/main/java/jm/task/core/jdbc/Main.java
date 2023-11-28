package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        userServiceImpl.createUsersTable();
        userServiceImpl.saveUser("Ivan", "Ivanov", (byte) 25);
        userServiceImpl.saveUser("Petr", "Petrov", (byte) 35);
        userServiceImpl.saveUser("Sidor", "Sidorov", (byte) 45);
        userServiceImpl.saveUser("Alexey", "Alexeev", (byte) 55);
        System.out.println(userServiceImpl.getAllUsers());
        userServiceImpl.cleanUsersTable();
        userServiceImpl.dropUsersTable();
    }
}
