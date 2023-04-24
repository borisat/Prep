package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        Util util = new Util();


        //Возврат и вывод списка юзеров
        UserServiceImpl userService = new UserServiceImpl();

        userService.getAllUsers();

        //Добавление юзера
        userService.saveUser("Ivasdfn", "adfadf", (byte) 27);


    }


}
