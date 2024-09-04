package v1.factory;

import v1.dao.Factura;
import v1.dao.Producto;
import v1.dao.Cliente;
import v1.dao.FacturaProducto;

public abstract class FactoryDAO {

    abstract Factura getFacturaDAO();
    abstract Cliente getClienteDAO();
    abstract Producto getProductoDAO();
    abstract FacturaProducto getFacturaProductoDAO();

    abstract boolean CreateDataBase();


}
