package v1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySqlClienteDAO implements Cliente{
    private Connection connection;
    private static Cliente instance;

    private MySqlClienteDAO(Connection connection) {
        this.connection = connection;
        this.instance = this;
    }

    public static Cliente getInstance(Connection connection) {
        if (instance == null) {
            instance = new MySqlClienteDAO(connection);
        }
        return instance;
    }


    @Override
    public boolean createTable() throws Exception {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS cliente (" +
                    "idCliente INT NOT NULL AUTO_INCREMENT," +
                    "nombre VARCHAR(500)," +
                    "email VARCHAR(150)," +
                    "PRIMARY KEY (idCliente))";
            this.connection.prepareStatement(sql).execute();
            this.connection.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insert(String nombre, String email) throws Exception {
        try {
            String sql = "INSERT INTO cliente (nombre, email) VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            this.connection.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ResultSet selectAll() {
        try {
            String sql = "SELECT * FROM cliente";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            preparedStatement.close();
            return resultSet;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
