package v1.factory;

import java.sql.*;

public class MySqlFactory {
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/";
    private final String NOMBRE_BD = "arquiweb-tp1-integrador";
    private final String USER = "root";
    private final String PASSWORD = "";
    private Connection connection;
    private static MySqlFactory instance;

    private MySqlFactory() {
        this.getConnection();
    }

    public static MySqlFactory getInstance() {
        if (instance == null) {
            instance = new MySqlFactory();
        }
        return instance;
    }

    private void getConnection() {
        try {
            Class.forName(DRIVER).getDeclaredConstructor();
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            this.connection.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}
