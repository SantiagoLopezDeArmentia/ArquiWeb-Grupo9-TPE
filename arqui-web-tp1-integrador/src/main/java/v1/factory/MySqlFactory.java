package v1.factory;

import v1.dao.*;

import java.sql.*;

public class MySqlFactory extends FactoryDAO{

    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/";
    private final String NOMBRE_BD = "aw_tp1_integrador";
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
            this.connection.createStatement();
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

    public Factura getFacturaDAO() {
        return MySqlFacturaDAO.getInstance(this.connection);
    }

    public Cliente getClienteDAO() {
        return MySqlClienteDAO.getInstance(this.connection);
    }

    public Producto getProductoDAO() {
        return MySqlProductoDAO.getInstance(this.connection);
    }

    public FacturaProducto getFacturaProductoDAO() {
        return MySqlFacturaProductoDAO.getInstance(this.connection);
    }

    public boolean CreateDataBase() {
        try {
            String sql = "CREATE DATABASE IF NOT EXISTS " + NOMBRE_BD;
            this.connection.prepareStatement(sql).execute();
            this.connection.commit();
            String useDataBaseSql = "USE " + NOMBRE_BD;
            this.connection.prepareStatement(useDataBaseSql).execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean relacionarTablaUnoMuchos(String fromTableName, String fromTableField, String toTableName,String toTableField, String nombreRelacion) throws Exception {
        try {
            String sql = "ALTER TABLE " + fromTableName  +
                    " ADD CONSTRAINT " + nombreRelacion +
                    " FOREIGN KEY (" + fromTableField +")"+
                    " REFERENCES " +  toTableName + "("+ toTableField +");";
            this.connection.prepareStatement(sql).execute();
            this.connection.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
