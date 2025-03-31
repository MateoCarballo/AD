import baseX.OperacionesBaseX;
import mongo.OperacionesMongo;
import org.basex.api.client.Session;
import org.basex.examples.api.BaseXClient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;


public class Main {
    public static OperacionesBaseX obx;

    public static void main(String[] args) {
        try {
            //obx = new OperacionesBaseX();
            //crearDatabaseBaseXVariosArchivos();
            //crearDatabaseBaseXArchivoUnico();
            //modificarExamenPorMateriaMetodoB("materia 3", "Materia modificada desde metodo B con execute");
            //obx.insertarDatosStarWars();
            //obx.session.execute("OPEN starWars");
            OperacionesMongo om = new OperacionesMongo();
            //om.liga6("34343434E");
            om.andres(1,10);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

