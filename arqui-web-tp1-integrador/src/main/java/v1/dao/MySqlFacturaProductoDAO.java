package v1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySqlFacturaProductoDAO implements FacturaProducto {
    private Connection connection;
    private static FacturaProducto instance;

    private MySqlFacturaProductoDAO(Connection connection) {
        this.connection = connection;
        this.instance = this;
    }

    public static FacturaProducto getInstance(Connection connection) {
        if (instance == null) {
            instance = new MySqlFacturaProductoDAO(connection);
        }
        return instance;
    }

    public boolean createTable() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS factura_producto (" +
                    "idFactura INT," +
                    "idProducto INT," +
                    "cantidad INT," +
                    "PRIMARY KEY (idFactura, idProducto))";
            this.connection.prepareStatement(sql).execute();
            this.connection.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insert(int idFactura, int idProducto, int cantidad) throws Exception {
        try {
            String sql = "INSERT INTO factura_producto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, idFactura);
            ps.setInt(2, idProducto);
            ps.setInt(3, cantidad);
            ps.executeUpdate();
            ps.close();
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
            String sql = "SELECT * FROM factura_producto";
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
