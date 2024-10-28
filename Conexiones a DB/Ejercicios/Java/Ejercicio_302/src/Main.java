import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        try{
            Connection conn = ConnMySQL.getInstance();
            consultaSelect(conn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static void consultaSelect(Connection con) {
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("Select nombre from empleado;");
            while(rs.next()){
                System.out.println("Nombre del empleado: "+ rs.getString(1));
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}