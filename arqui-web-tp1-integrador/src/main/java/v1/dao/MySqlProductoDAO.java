package v1.dao;

import java.sql.*;

public class MySqlProductoDAO implements  Producto{

    private Connection connection;
    private static MySqlProductoDAO instance;

    private MySqlProductoDAO(Connection connection) {
        this.connection = connection;
        this.instance = this;
    }

    public static Producto getInstance(Connection connection) {
        if (instance == null) {
            instance = new MySqlProductoDAO(connection);
        }
        return instance;
    }

    @Override
    public boolean createTable() throws Exception {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS producto (" +
                    "idProducto INT NOT NULL AUTO_INCREMENT," +
                    "nombre VARCHAR(45) NOT NULL," +
                    "valor FLOAT NOT NULL," +
                    "PRIMARY KEY(idProducto))";
            this.connection.prepareStatement(sql).execute();
            this.connection.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insert(String nombre, Float valor) throws Exception {
        try {
            String sql = "INSERT INTO producto (nombre, valor) VALUES (?,?)";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setFloat(2, valor);
            ps.execute();
            ps.close();
            this.connection.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet selectAll() {
        try {
            String sql = "SELECT * FROM producto";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ps.close();
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
