package v1.dao;
import java.sql.ResultSet;

public interface Cliente {

    public boolean createTable() throws Exception;
    //public boolean relacionarTabla(String tableName, String ownerField, String otherField) throws Exception;
    public boolean insert(String nombre, String email) throws Exception;
    public ResultSet selectAll();
    //public ResultSet selectBy(String filterField, String filterValue) throws Exception;
}
