package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserServiceImpl userService = new UserServiceImpl();


        //Создание таблицы User(ов)
        userService.createUsersTable();

        //Добавление 4 User(ов)
        for (int i = 0; i < 4; i++) {
            userService.saveUser("Имя" + i, "Фамилия" + i, (byte) (i + 20));
        }

        //Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
        userService.getAllUsers();

        //Удаление юзера
        userService.removeUserById(1);

        //Очистка таблицы User(ов)
        userService.cleanUsersTable();

        //Удаление таблицы
        userService.dropUsersTable();


    }


}
