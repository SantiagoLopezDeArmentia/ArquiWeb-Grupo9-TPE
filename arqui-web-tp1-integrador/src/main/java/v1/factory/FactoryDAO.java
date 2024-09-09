package v1.factory;

import v1.dao.Factura;
import v1.dao.Producto;
import v1.dao.Cliente;
import v1.dao.FacturaProducto;
import java.sql.Connection;

public abstract class FactoryDAO {

    abstract Factura getFacturaDAO();
    abstract Cliente getClienteDAO();
    abstract Producto getProductoDAO();
    abstract FacturaProducto getFacturaProductoDAO();
    abstract  Connection getConnection();

    abstract boolean CreateDataBase();


}
