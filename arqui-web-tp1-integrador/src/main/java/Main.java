import v1.dao.*;
import v1.factory.FactoryDAO;
import v1.factory.MySqlFactory;


public class Main {
    public static void main(String[] args) {

        MySqlFactory mysql = MySqlFactory.getInstance();

        Cliente cliente = mysql.getClienteDAO();
        Factura factura = mysql.getFacturaDAO();
        Producto producto = mysql.getProductoDAO();

        try {
            mysql.CreateDataBase();
            System.out.println("Base de datos creada.");
            cliente.createTable();
            System.out.println("Tabla de Clientes creada.");
            factura.createTable();
            System.out.println("Tabla de Facturas Creada.");
            producto.createTable();
            System.out.println("Tabla de Productos Creada.");

        }catch (Exception e) {
            e.printStackTrace();
            mysql.closeConnection();
            System.exit(1);
        }


        mysql.closeConnection();






    }
}
