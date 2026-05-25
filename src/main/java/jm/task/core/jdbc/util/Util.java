package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;
import java.util.Properties;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/mysql?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "Medic333";

    private static Connection connection = null;
    private static SessionFactory sessionFactory = null;

    private Util() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Подключение к БД установлено");
            } catch (SQLException e) {
                System.err.println("Ошибка подключения к БД");
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Подключение к БД закрыто");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties properties = new Properties();
                properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                properties.setProperty("hibernate.connection.url", URL);
                properties.setProperty("hibernate.connection.username", USER);
                properties.setProperty("hibernate.connection.password", PASSWORD);
                properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
                properties.setProperty("hibernate.show_sql", "true");
                properties.setProperty("hibernate.hbm2ddl.auto", "none");

                sessionFactory = new Configuration()
                        .setProperties(properties)
                        .addAnnotatedClass(User.class)
                        .buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}

