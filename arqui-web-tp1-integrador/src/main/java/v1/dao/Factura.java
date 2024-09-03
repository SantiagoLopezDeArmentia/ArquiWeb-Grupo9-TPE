package v1.dao;

import java.sql.ResultSet;

public interface Factura {
    public boolean createTable() throws Exception;
    public boolean relacionarTabla(String tableName, String ownerField, String otherField, String nombreRelacion) throws Exception;
    public boolean insert(int idCliente) throws Exception;
    public ResultSet selectAll();
}
