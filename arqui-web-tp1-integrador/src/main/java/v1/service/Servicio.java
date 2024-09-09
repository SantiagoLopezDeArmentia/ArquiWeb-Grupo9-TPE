package v1.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import v1.dao.*;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Servicio {

    public void insertarCliente(Cliente cliente, String rutaArchivo) {
        try {
            System.out.println("#### INICIO INSERCION CLIENTES ####");

            File file = new File(rutaArchivo);
            System.out.println(file.getCanonicalPath());
            FileReader fileReader = new FileReader(file.getCanonicalFile());
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(fileReader);
            for(CSVRecord row: parser) {
                cliente.insert(row.get("nombre"), row.get("email"));
            }
            System.out.println("#### FIN INSERCION CLIENTES ####");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void insertarProducto(Producto producto, String rutaArchivo) {
        try {
            System.out.println("#### INICIO INSERCION PRODUCTOS ####");
            File file = new File(rutaArchivo);
            FileReader fileReader = new FileReader(file.getCanonicalFile());
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(fileReader);
            for(CSVRecord row: parser) {
                producto.insert(row.get("nombre"), Float.parseFloat(row.get("valor")));
            }
            System.out.println("#### FIN INSERCION PRODUCTOS ####");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void insertarFactura(Factura factura, String rutaArchivo) {
        try {
            System.out.println("#### INICIO INSERCION FACTURAS ####");
            File file = new File(rutaArchivo);
            FileReader fileReader = new FileReader(file.getCanonicalFile());
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(fileReader);
            for(CSVRecord row: parser) {
                factura.insert(Integer.parseInt(row.get("idFactura")), Integer.parseInt(row.get("idCliente")));
            }
            System.out.println("#### FIN INSERCION FACTURAS ####");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }


    public void insertarFacturaProducto(FacturaProducto facturaProducto, String rutaArchivo) {
        try {
            System.out.println("#### INICIO INSERCION FACTURAS-PRODUCTOS ####");
            File file = new File(rutaArchivo);
            FileReader fileReader = new FileReader(file.getCanonicalFile());
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(fileReader);
            for(CSVRecord row: parser) {
                facturaProducto.insert(Integer.parseInt(row.get("idFactura")), Integer.parseInt(row.get("idProducto")), Integer.parseInt(row.get("cantidad")));
            }
            System.out.println("#### FIN INSERCION FACTURAS-PRODUCTOS ####");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public ResultSet servicio3(Connection connection) {
        try {
            /*String sql = "SELECT p.*, p.valor * fp.cantidad as \"cantidad\" FROM producto p" +
                    " JOIN factura_producto fp ON p.idProducto = fp.idProducto" +
                    " ORDER BY cantidad desc" +
                    " LIMIT 1";*/
            String sql = "SELECT count(fp.cantidad) FROM factura_producto fp" +
                    " JOIN producto p ON fp.idProducto = p.idProducto" +
                    " ORDER BY cantidad desc" +
                    " LIMIT 1";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            //ps.close();
            connection.commit();
            return rs;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
