package v1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySqlFacturaDAO implements Factura {
    private Connection connection;
    private static MySqlFacturaDAO instance;

    private MySqlFacturaDAO(Connection connection) {
        this.connection = connection;
        this.instance = this;
    }

    public static Factura getInstance(Connection connection) {
        if (instance == null) {
            instance = new MySqlFacturaDAO(connection);
        }
        return instance;
    }

    @Override
    public boolean createTable() throws Exception {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS factura (" +
                    "idFactura INT NOT NULL AUTO_INCREMENT," +
                    "idCliente INT NOT NULL," +
                    "PRIMARY KEY (idFactura))";
            this.connection.prepareStatement(sql).execute();
            this.connection.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean relacionarTabla(String tableName, String ownerField, String otherField, String nombreRelacion) throws Exception {
        try {
            String sql = "ALTER TABLE factura" +
                    " ADD CONSTRAINT " + nombreRelacion +
                    " FOREIGN KEY " + ownerField +
                    " REFERENCES " +  tableName + "("+otherField+")";
            this.connection.prepareStatement(sql).execute();
            this.connection.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insert(int idCliente) throws Exception {
        try {
            String sql = "INSERT INTO factura (idCliente) VALUES (?)";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, idCliente);
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
            String sql = "SELECT * FROM factura";
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
