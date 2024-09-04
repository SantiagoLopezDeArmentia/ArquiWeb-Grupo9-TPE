package v1.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;

public class Servicio {

    public void otro() {
        try {
            FileReader fileReader = new FileReader("productos.csv");
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(fileReader);

            for(CSVRecord row: parser) {
                System.out.println(row.get("idProducto"));
                System.out.println(row.get("nombre"));
                System.out.println(row.get("valor"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }
}
