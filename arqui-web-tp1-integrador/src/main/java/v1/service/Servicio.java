package v1.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import v1.dao.*;

import java.io.File;
import java.io.FileReader;

public class Servicio {

    public void insertarCliente(Cliente cliente, String rutaArchivo) {
        try {
            System.out.println("#### INICIO INSERCION CLIENTES ####");
            FileReader fileReader = new FileReader(rutaArchivo);
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
            FileReader fileReader = new FileReader(rutaArchivo);
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
            FileReader fileReader = new FileReader(rutaArchivo);
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
            FileReader fileReader = new FileReader(rutaArchivo);
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


}
