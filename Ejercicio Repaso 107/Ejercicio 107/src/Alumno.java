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
        for (int i = 1; i < datosJuntos.length-1; i++) {
            notasMaterias[i-1]=Integer.parseInt(datosJuntos[i]);
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
