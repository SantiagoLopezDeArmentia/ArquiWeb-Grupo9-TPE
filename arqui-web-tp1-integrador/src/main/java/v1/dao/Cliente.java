package v1.dao;
import java.sql.ResultSet;

public interface Cliente {

    public boolean createTable() throws Exception;
    public boolean insert(String nombre, String email) throws Exception;
    public ResultSet selectAll();
    public ResultSet servicio4();
}
