package net.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection("jdbc:hsqldb:file:db/xdb", "SA", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
