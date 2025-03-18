import org.basex.api.client.Session;
import org.basex.examples.api.BaseXClient;

import java.io.IOException;

public class ConexionBaseX {
    private static BaseXClient session;
    public static final String BASE_X_HOST = "localhost";
    public static final int PORT = 1984;
    public static final String USER = "admin";
    public static final String PWD = "abc123";
    public static final String BASEX_DATABASE_NAME = "videojuegos";

    private ConexionBaseX() {
        try {
            session = new BaseXClient(BASE_X_HOST, PORT, USER, PWD);
        } catch (IOException e) {
            System.out.println("ERROR al crear la conexion con BaseX");
        }
    }

    public static BaseXClient getSession() {
        if (session == null){
            new ConexionBaseX();
        }
        try {
            session.execute("open " + BASEX_DATABASE_NAME);
        } catch (IOException e) {
            System.out.println("Error al abrir la base de datos \n" + e.getMessage());
        }
        return session;
    }

    public static void closeSession(){
        if (session != null){
            try {
                session.close();
            } catch (IOException e) {
                System.out.println("ERROR al cerra la conexion con BaseX");
            }
        }
    }
}
