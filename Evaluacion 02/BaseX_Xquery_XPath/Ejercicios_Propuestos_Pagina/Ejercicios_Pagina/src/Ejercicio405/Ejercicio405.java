package Ejercicio405;


import org.basex.examples.api.BaseXClient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Ejercicio405 {
    final static String HOST = "localhost";
    final static String USER = "admin";
    final static String PWD = "abc123";
    final static int PORT = 1984;
    final static String CONSULTA_1 = "<losbailes>\n" +
            "{for $nombre in db:get('Ejercicio405')/bailes/baile/nombre\n" +
            "return $nombre}\n" +
            "</losbailes>";
    final static String CONSULTA_2 = "<losbailes>\n" +
            "{for $baile in /bailes/baile\n" +
            "return <baile>{concat($baile/nombre/text(),\" (\", $baile/plazas/text(), \")\")}</baile>}\n" +
            "</losbailes>";
    final static String CONSULTA_3 = "for $nombre in db:get('Ejercicio405')/bailes/baile[precio < 30]/nombre\n" +
            "return $nombre";
    final static String CONSULTA_4 = "for $nombre in db:get('Ejercicio405')/bailes/baile[precio > 30 and precio/@moneda/data() = 'euro']/nombre\n" +
            "return $nombre";
    final static String CONSULTA_5 = "for $baile in db:get('Ejercicio405')/bailes/baile\n" +
            "where contains($baile/comienzo,\"/1/\")" +
            "return <baile>" +
            "{$baile/nombre}" +
            "{$baile/comienzo}" +
            "</baile>";
    final static String CONSULTA_6 = "for $baile in db:get('Ejercicio405')/bailes/baile/\n" +
            "order by $baile/sala\n" +
            "return {<baile>concat($baile/profesor,' ',$baile/sala)</baile>}";

    final static String CONSULTA_7 = "";

    final static String CONSULTA_8 = """
             for $media in avg(//precio)
             return <media>{$media}</media>
            """;

    final static String CONSULTA_9 = """
            sum(/bailes/baile[sala = 1]/precio)
            """;
    final static String CONSULTA_10 = """
            sum(/bailes/baile[profesor/text() = 'Jesus Lozano']/plazas)
            """;

    final static String CONSULTA_11 = """
            /bailes/baile[profesor/text() = 'Laura Mendiola']/precio * /bailes/baile[profesor/text() = 'Laura Mendiola']/plazas
            """;
    /**
     * El dinero que ganaría el profesor Jesus Lozano si se completaran
     * todas las plazas de su baile, pero mostrando el beneficio de cada baile por separado.
     */
    final static String CONSULTA_12 = """
            for $baile in /bailes/baile[profesor ='Jesus Lozano']
            return <precio NumSala = '{$baile/sala}'>{$baile/precio * $baile/plazas}</precio>
            """;

    final static String USE_DATABASE_EJ405 = "open Ejercicio405";

    public static void main(String[] args) {
        try (BaseXClient session = new BaseXClient(HOST, PORT, USER, PWD)) {
            //ejecutarConsulta(session,CONSULTA_1,USE_DATABASE_EJ405);
            //ejecutarConsulta(session,CONSULTA_2,USE_DATABASE_EJ405);
            //ejecutarConsulta(session,CONSULTA_3,USE_DATABASE_EJ405);
            //ejecutarConsulta(session,CONSULTA_4,USE_DATABASE_EJ405);
            //ejecutarConsulta(session,CONSULTA_5,USE_DATABASE_EJ405);
            //ejecutarConsulta(session,CONSULTA_6,USE_DATABASE_EJ405);
            //ejecutarConsulta(session,CONSULTA_7,USE_DATABASE_EJ405);
            //ejecutarConsulta(session,CONSULTA_8,USE_DATABASE_EJ405);
            //ejecutarConsulta(session,CONSULTA_9,USE_DATABASE_EJ405);
            //ejecutarConsulta(session,CONSULTA_10,USE_DATABASE_EJ405);
            //ejecutarConsulta(session,CONSULTA_11,USE_DATABASE_EJ405);
            //ejecutarConsulta(session,CONSULTA_12,USE_DATABASE_EJ405);
            ejecutarConsulta(session,CONSULTA_13,USE_DATABASE_EJ405);

        } catch (IOException e) {
            System.out.println("Error al conectarme con BaseX");
            e.printStackTrace();
        }
    }

    private static void ejecutarConsulta(BaseXClient session, String queryAsString, String command) {
        try {
            session.execute(command);
            BaseXClient.Query query = session.query(queryAsString);
            while (query.more()) {
                System.out.println(query.next());
            }
        } catch (IOException e) {
            System.out.println("Error en la consulta");
            e.printStackTrace();
        }
    }
}
