import java.lang.reflect.Array;
import java.util.Arrays;

public class Alumno {

    String nombre;
    int [] notasMaterias;
    public Alumno() {
    }

    public Alumno(String datosBrutos) {
        conseguirNotas(datosBrutos);

    }

    private void conseguirNotas(String datosBrutos){
        String [] datosJuntos =datosBrutos.split("#");
        this.nombre=datosJuntos[0];
        this.notasMaterias=new int[datosJuntos.length-1];
        try {
            for (int i = 1; i < datosJuntos.length-1; i++) {
                if (datosJuntos[i].equalsIgnoreCase(" ")){
                    this.notasMaterias[i-1]=Integer.parseInt(datosJuntos[i].trim());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "nombre='" + nombre + '\'' +
                ", notasMaterias=" + Arrays.toString(notasMaterias) +
                '}';
    }
}
