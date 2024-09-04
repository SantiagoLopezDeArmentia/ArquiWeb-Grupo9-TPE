package v1.dao;

import java.sql.ResultSet;

public interface Producto {
    public boolean createTable() throws Exception;
    public boolean insert(String nombre, Float valor) throws Exception;
    public ResultSet selectAll();

}
