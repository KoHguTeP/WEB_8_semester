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

        //task1();

        //task2();

        //task3();

        windowsForm();


        /*if (connection != null) {
            System.out.println("Вы успешно подключились к БД");
        } else {
            System.out.println("Не удалось установить соединение с базой данных");
        }*/
    }

    private static void windowsForm(){
        try {
            JFrame jFrame = new JFrame("Приложение");
            jFrame.setContentPane(new App().getPanelMain());
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            jFrame.pack();
            jFrame.setVisible(true);
            //jFrame.setResizable(false);
        }
        catch (Exception e) {
        }
    }

    private static void task1() {
        System.out.println("Задание 1");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver не найден. Включите его в путь к вашей библиотеке");
            e.printStackTrace();
            return;
        }

        //Connection connection = null;

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);){
            System.out.println("PostgreSQL JDBC Driver успешно подключен");
            Statement statement = connection.createStatement();

            System.out.println("Результаты запросов");

            statement.executeUpdate("INSERT INTO singer VALUES (0006, 'new singer')");
            statement.executeUpdate("UPDATE singer SET name = 'new_name' WHERE ID_singer = 0011");
            statement.executeUpdate("DELETE FROM singer WHERE ID_singer = 0010");

            ResultSet resultSet = statement.executeQuery("SELECT a.name FROM album AS a INNER JOIN singer s ON a.ID_singer = s.ID_singer WHERE s.name = 'XXXTENTACION';");

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        }
        catch (SQLException e) {
            System.out.println("Ошибка SQL запроса \n" + e);
            //e.printStackTrace();
            return;
        }

    }

    private static void task2(){
        System.out.println("Задание 2");

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

            System.out.println("Результаты запросов");

            PreparedStatement query1 = connection.prepareStatement("INSERT INTO singer VALUES (?,?)");
            PreparedStatement query2 = connection.prepareStatement("UPDATE singer SET name = ? WHERE ID_singer = ?");
            PreparedStatement query3 = connection.prepareStatement("DELETE FROM singer WHERE ID_singer = ?");
            PreparedStatement query4 = connection.prepareStatement("SELECT a.name FROM album AS a INNER JOIN singer s ON a.ID_singer = s.ID_singer WHERE s.name = ?");

            query1.setInt(1, 0007);
            query1.setString(2, "Новый певец");
            query1.executeUpdate();

            query2.setString(1, "Переименовал");
            query2.setInt(2, 0011);
            query2.executeUpdate();

            query3.setInt(1,0006);
            query3.executeUpdate();

            query4.setString(1, "XXXTENTACION");
            ResultSet resultSet = query4.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        }
        catch (SQLException e) {
            System.out.println("Ошибка SQL запроса \n" + e);
            //e.printStackTrace();
            return;
        }
    }

    private static void task3(){
        System.out.println("Задание 3");

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

            System.out.println("Результаты запросов");

            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(connection.getCatalog(), "public", "%", new String[]{"TABLE"});//databaseMetaData.getTables("MusicShop", "tables", null, new String[]{"TABLE","VIEW"});
            //ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String [] {"TABLE"});
            System.out.println("Таблицы:");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(3));//("TABLE_NAME"));
            }
            System.out.println("Столбцы:");
            resultSet = databaseMetaData.getColumns(connection.getCatalog(),null, "album", "%");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("COLUMN_NAME"));
            }
        }
        catch (SQLException e) {
            System.out.println("Ошибка SQL запроса \n" + e);
            //e.printStackTrace();
            return;
        }
    }
}


