package v1.dao;

import java.sql.Connection;

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


}
