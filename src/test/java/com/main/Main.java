package com.main;


import com.GUI.App;

import javax.swing.*;
import java.sql.*;

public class Main {
    //  Database credentials
    static final String DB_URL = "jdbc:postgresql://localhost:5432/MusicShop";
    static final String USER = "postgres";
    static final String PASS = "0000";

    public static void main(String[] args) {

        JFrame jFrame = new JFrame("Приложение");
        jFrame.setContentPane(new App().getPanelMain());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);

        /*System.out.println("Проверка соединения с PostgreSQL JDBC");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver не найден. Включите его в путь к вашей библиотеке");
            e.printStackTrace();
            return;
        }

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("PostgreSQL JDBC Driver успешно подключен");
            Statement statement = connection.createStatement();

            System.out.println("\n Результаты запросов \n");

            //statement.executeUpdate("INSERT INTO singer VALUES (0006, 'new singer')");
            //statement.executeUpdate("UPDATE singer SET name = 'new_name' WHERE ID_singer = 0011");
            //statement.executeUpdate("DELETE FROM singer WHERE ID_singer = 0010");

            ResultSet resultSet = statement.executeQuery("SELECT name || ', ' || duration AS \"Song Details\" FROM composition;");

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }

            System.out.println("\n Запросы выполнены");

        } catch (SQLException e) {
            System.out.println("Ошибка подключения");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("Вы успешно подключились к БД");
        } else {
            System.out.println("Не удалось установить соединение с базой данных");
        }*/
    }
}
