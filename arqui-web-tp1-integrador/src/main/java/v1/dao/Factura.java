package v1.dao;

import java.sql.ResultSet;

public interface Factura {
    public boolean createTable() throws Exception;
    public boolean insert(int idFactura, int idCliente) throws Exception;
    public ResultSet selectAll();
}
