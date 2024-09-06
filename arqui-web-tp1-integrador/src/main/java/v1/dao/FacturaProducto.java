package v1.dao;

import java.sql.Connection;
import java.sql.ResultSet;

public interface FacturaProducto {

    abstract boolean createTable();
    public boolean insert(int idFactura, int idProducto, int cantidad) throws Exception;
    public ResultSet selectAll();
}
