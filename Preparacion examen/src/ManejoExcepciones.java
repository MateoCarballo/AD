import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;

public class ManejoExcepciones {

    public static void main(String[] args) {
        // Probamos el método simulando diferentes errores con valores de 1 a 5
        for (int i = 1; i <= 5; i++) {
            try {
                simularErrores(i);
            } catch (ParserConfigurationException e) {
                System.err.println("Error de configuración del parser XML: " + e.getMessage());
                e.printStackTrace();
            } catch (TransformerException e) {
                System.err.println("Error de transformación del XML: " + e.getMessage());
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                System.err.println("Error: Archivo no encontrado: " + e.getMessage());
                e.printStackTrace();
            } catch (InputMismatchException e) {
                System.err.println("Error de entrada: Tipo de dato incorrecto: " + e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                System.err.println("Error de E/S: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println("Error inesperado: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * Simula diferentes tipos de excepciones en función del valor del parámetro.
     * @param codigoError Número que indica qué excepción lanzar.
     * @throws ParserConfigurationException Si codigoError es 1.
     * @throws TransformerException Si codigoError es 2.
     * @throws FileNotFoundException Si codigoError es 3.
     * @throws InputMismatchException Si codigoError es 4.
     * @throws IOException Si codigoError es 5.
     */
    private static void simularErrores(int codigoError) throws ParserConfigurationException, TransformerException,
            FileNotFoundException, InputMismatchException, IOException {
        switch (codigoError) {
            case 1:
                throw new ParserConfigurationException("Fallo en la configuración del parser.");
            case 2:
                throw new TransformerException("Error al transformar el documento XML.");
            case 3:
                throw new FileNotFoundException("El archivo especificado no fue encontrado.");
            case 4:
                throw new InputMismatchException("Se esperaba un tipo de dato diferente.");
            case 5:
                throw new IOException("Error de entrada/salida durante el proceso.");
            default:
                System.out.println("No hay errores para este valor: " + codigoError);
        }
    }
}
