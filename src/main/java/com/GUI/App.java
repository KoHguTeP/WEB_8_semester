package com.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class App {
    private JButton b_insert;
    private JButton b_delete;
    private JButton b_update;
    private JButton b_run;
    private JTextArea tA_result;
    private JPanel panelMain;
    private JTextField tF_ID_singer_1;
    private JTextField tF_name_singer_1;
    private JTextField tF_ID_singer_2;
    private JTextField tF_ID_singer_3;
    private JTextField tF_name_singer_2;
    private JTextField tF_name_singer_3;
    private JButton button1;

    static final String DB_URL = "jdbc:postgresql://localhost:5432/MusicShop";
    static final String USER = "postgres";
    static final String PASS = "0000";

    public App() {


        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "PostgreSQL JDBC Driver не найден. Включите его в путь к вашей библиотеке");
            return;
        }

        b_insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tF_ID_singer_1.getText().equals("ID")) {
                    Connection connection = null;

                    try {
                        connection = DriverManager.getConnection(DB_URL, USER, PASS);

                        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO singer VALUES (?,?)");

                        preparedStatement.setInt(1, Integer.parseInt(tF_ID_singer_1.getText()));
                        preparedStatement.setString(2, tF_name_singer_1.getText());
                        preparedStatement.executeUpdate();

                        tA_result.setText("Добавление выполнено");

                        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM singer");
                        String res = "";

                        while (resultSet.next()) {
                            res += resultSet.getString(1) + "     " + resultSet.getString(2) + "\n";
                        }
                        tA_result.setText(res);

                    } catch (SQLException er) {
                        JOptionPane.showMessageDialog(null, "Ошибка SQL запроса");
                        tA_result.setText(er.toString());
                        return;
                    }

                    if (connection == null) {
                        JOptionPane.showMessageDialog(null, "Не удалось установить соединение с базой данных");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Вы не ввели ID");
                }
            }
        });

        b_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tF_ID_singer_2.getText().equals("ID")) {
                    Connection connection = null;

                    try {
                        connection = DriverManager.getConnection(DB_URL, USER, PASS);

                        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM singer WHERE ID_singer = ?");

                        preparedStatement.setInt(1, Integer.parseInt(tF_ID_singer_2.getText()));
                        preparedStatement.executeUpdate();

                        tA_result.setText("Удаление выполнено");

                        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM singer");
                        String res = "";

                        while (resultSet.next()) {
                            res += resultSet.getString(1) + "     " + resultSet.getString(2) + "\n";
                        }
                        tA_result.setText(res);

                    } catch (SQLException er) {
                        JOptionPane.showMessageDialog(null, "Ошибка SQL запроса");
                        tA_result.setText(er.toString());
                        return;
                    }

                    if (connection == null) {
                        JOptionPane.showMessageDialog(null, "Не удалось установить соединение с базой данных");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Вы не ввели ID");
                }
            }
        });

        b_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tF_ID_singer_3.getText().equals("ID")) {
                    Connection connection = null;

                    try {
                        connection = DriverManager.getConnection(DB_URL, USER, PASS);

                        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE singer SET name = ? WHERE ID_singer = ?");

                        preparedStatement.setString(1, tF_name_singer_2.getText());
                        preparedStatement.setInt(2, Integer.parseInt(tF_ID_singer_3.getText()));
                        preparedStatement.executeUpdate();

                        tA_result.setText("Изменение выполнено");

                        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM singer");
                        String res = "";

                        while (resultSet.next()) {
                            res += resultSet.getString(1) + "     " + resultSet.getString(2) + "\n";
                        }
                        tA_result.setText(res);



                    } catch (SQLException er) {
                        JOptionPane.showMessageDialog(null, "Ошибка SQL запроса");
                        tA_result.setText(er.toString());
                        return;
                    }

                    if (connection == null) {
                        JOptionPane.showMessageDialog(null, "Не удалось установить соединение с базой данных");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Вы не ввели ID");
                }
            }
        });

        b_run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connection = null;

                try {
                    connection = DriverManager.getConnection(DB_URL, USER, PASS);

                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT a.name FROM album AS a INNER JOIN singer s ON a.ID_singer = s.ID_singer WHERE s.name = ?");

                    preparedStatement.setString(1, tF_name_singer_3.getText());
                    ResultSet resultSet = preparedStatement.executeQuery();
                    String res = "";

                    while (resultSet.next()) {
                        res += resultSet.getString(1) + "\n";
                    }
                    tA_result.setText(res);

                } catch (SQLException er) {
                    JOptionPane.showMessageDialog(null, "Ошибка SQL запроса");
                    tA_result.setText(er.toString());
                    return;
                }

                if (connection == null) {
                    JOptionPane.showMessageDialog(null, "Не удалось установить соединение с базой данных");
                }
            }
        });





    }

    public JPanel getPanelMain(){
        return panelMain;
    }
}
