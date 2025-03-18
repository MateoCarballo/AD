import org.basex.api.client.Session;
import org.basex.examples.api.BaseXClient;

import java.io.IOException;

public class ConexionBaseX {
    private static BaseXClient session;
    private static final String BASE_X_HOST = "localhost";
    private static final int PORT = 1984;
    private static final String USER = "admin";
    private static final String PWD = "abc123";

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
