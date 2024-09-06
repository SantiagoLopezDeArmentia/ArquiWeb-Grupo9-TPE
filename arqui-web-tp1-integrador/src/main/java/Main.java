import v1.dao.*;
import v1.factory.FactoryDAO;
import v1.factory.MySqlFactory;
import v1.service.Servicio;

import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {
    public static void main(String[] args) {

        final String ROOT_PROJECT = "./arqui-web-tp1-integrador/datasets/";
        final String NB_FILE_CLIENTES = "arquiweb_clientes.csv";
        final String NB_FILE_FACTURAS = "arquiweb_facturas.csv";
        final String NB_FILE_PRODUCTOS = "arquiweb_productos.csv";
        final String NB_FILE_FACTURAS_PRODUCTOS = "arquiweb_facturas-productos.csv";
        String generateRelations = "1";

        MySqlFactory mysql = MySqlFactory.getInstance();

        Cliente cliente = mysql.getClienteDAO();
        Factura factura = mysql.getFacturaDAO();
        Producto producto = mysql.getProductoDAO();
        FacturaProducto facturaProducto = mysql.getFacturaProductoDAO();

        try {
            System.out.println("1. ####### GENERAR BASE DE DATOS / TABLAS");
            mysql.CreateDataBase();
            System.out.println("2. ####### GENERAR TABLAS");
            System.out.println("Base de datos creada.");
            cliente.createTable();
            System.out.println("Tabla de Clientes creada.");
            factura.createTable();
            System.out.println("Tabla de Facturas Creada.");
            producto.createTable();
            System.out.println("Tabla de Productos Creada.");
            facturaProducto.createTable();


            System.out.println("3. #### GENERAR RELACIONES");
            if (Boolean.parseBoolean(generateRelations)) {
                mysql.relacionarTablaUnoMuchos("factura", "idCliente", "cliente", "idCliente", "RELACION_CLIENTE_FACTURA");
                mysql.relacionarTablaUnoMuchos("factura_producto", "idFactura", "factura", "idFactura", "RELACION_FACTURAPRODUCTO_FACTURA");
                mysql.relacionarTablaUnoMuchos("factura_producto", "idProducto", "producto", "idProducto", "RELACION_FACTURAPRODUCTO_PRODUCTO");
            }
            System.out.println("4. ##### GENERAR INSERTAR DATOS");

            Servicio servicio = new Servicio();
            servicio.insertarCliente(cliente, Paths.get(ROOT_PROJECT, NB_FILE_CLIENTES).toString());
            servicio.insertarFactura(factura, Paths.get(ROOT_PROJECT, NB_FILE_FACTURAS).toString());
            servicio.insertarProducto(producto, Paths.get(ROOT_PROJECT, NB_FILE_PRODUCTOS).toString());
            servicio.insertarFacturaProducto(facturaProducto, Paths.get(ROOT_PROJECT, NB_FILE_FACTURAS_PRODUCTOS).toString());


        }catch (Exception e) {
            e.printStackTrace();
            mysql.closeConnection();
            System.exit(1);
        }


        mysql.closeConnection();

    }
}
