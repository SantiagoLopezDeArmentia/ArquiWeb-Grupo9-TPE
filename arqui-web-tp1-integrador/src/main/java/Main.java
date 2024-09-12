import v1.dao.*;
import v1.factory.FactoryDAO;
import v1.factory.MySqlFactory;
import v1.service.Servicio;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) {

        /* Para poder hacer pruebas en otro ambiente,
         * debera cambiarse el valor de la ruta
         */
        final String ROOT_PROJECT = "./ArquiWeb-Grupo9-TPE/arqui-web-tp1-integrador/datasets/";
        final String NB_FILE_CLIENTES = "arquiweb_clientes.csv";
        final String NB_FILE_FACTURAS = "arquiweb_facturas.csv";
        final String NB_FILE_PRODUCTOS = "arquiweb_productos.csv";
        final String NB_FILE_FACTURAS_PRODUCTOS = "arquiweb_facturas-productos.csv";
        /* Se utiliza para manejar la creacion de las relaciones entre las tablas */
        boolean generateRelations = false;
        /* Se utiliza para manejar la carga de datos a la base de datos */
        boolean loadData = false;

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
            /*
             * Generar relaciones entre tablas.
             *
             * Se hace uso de una variable booleana,
             * la cual debe cambiarse el valor en la declaracion de variables [generateRelations]
             * para poder manejar en las pruebas en la creacion de relaciones entre tablas.
             */
            if (generateRelations) {
                mysql.relacionarTablaUnoMuchos("factura", "idCliente", "cliente", "idCliente",
                        "RELACION_CLIENTE_FACTURA");
                mysql.relacionarTablaUnoMuchos("factura_producto", "idFactura", "factura", "idFactura",
                        "RELACION_FACTURAPRODUCTO_FACTURA");
                mysql.relacionarTablaUnoMuchos("factura_producto", "idProducto", "producto", "idProducto",
                        "RELACION_FACTURAPRODUCTO_PRODUCTO");
            }
            System.out.println("4. ##### GENERAR INSERTAR DATOS");

            /*
             * Servicio de carga de datos a la base de datos.
             *
             * Se hace uso de una variable booleana,
             * la cual debe cambiarse el valor en la declaracion de variables [loadData]
             * para poder manejar en las pruebas la carga de datos.
             */

            Servicio servicio = new Servicio();
            if (loadData) {
                System.out.println(Paths.get(ROOT_PROJECT, NB_FILE_CLIENTES).toString());
                servicio.insertarCliente(cliente, Paths.get(ROOT_PROJECT, NB_FILE_CLIENTES).toString());
                servicio.insertarFactura(factura, Paths.get(ROOT_PROJECT, NB_FILE_FACTURAS).toString());
                servicio.insertarProducto(producto, Paths.get(ROOT_PROJECT, NB_FILE_PRODUCTOS).toString());
                servicio.insertarFacturaProducto(facturaProducto,
                        Paths.get(ROOT_PROJECT, NB_FILE_FACTURAS_PRODUCTOS).toString());
            }

            /* Servicio 3
             *   Ejecucion del servicio 3 y muestra de resultados.
             * */

            ResultSet rs_serv3 = servicio.servicio3(mysql.getConnection());
            while (rs_serv3.next()) {
                System.out.println("id: " + rs_serv3.getInt("idProducto") +
                        " nombre: " + rs_serv3.getString("nombre") +
                        " costo: " + rs_serv3.getFloat("valor"));
            }

            /* Servicio 4
            *   Ejecucion del servicio 4 y muestra de resultados.
            * */
            ResultSet rs_serv4 = servicio.servicio4(mysql.getConnection());
            while (rs_serv4.next()) {
                System.out.println("Cliente: [ idCliente: " + rs_serv4.getInt("idCliente") +
                        " Nombre: " + rs_serv4.getString("nombre") +
                        " email: " + rs_serv4.getString("email") + " ] ");
            }

            ResultSet resultado = producto.selectAll();
            while (resultado.next()) {
                System.out.println("id: " + resultado.getInt("idProducto") + " nombre " + resultado.getString("nombre")
                        + " cantidad " + resultado.getFloat("valor"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            mysql.closeConnection();
            System.exit(1);
        }

        mysql.closeConnection();

    }
}
