import java.io.IOException;
import java.util.ArrayList;

public class Agenda {

    ArrayList<Contacto> listaContactos;

    public Agenda() {
        /* TODO: Tengo dudas de si esto deberia verse como esta o this.listaContactos = new ArrayList<Contacto>(); */
        this.listaContactos = new ArrayList<>();
    }

    public void anadirContacto() throws IOException {
        this.listaContactos.add(crearContacto());
    }

    public Contacto crearContacto(){

        String nombre= Main.preguntaDevuelveString("Nombre del usuario?");
        String apellidos= Main.preguntaDevuelveString("Apellidos del usuario?");
        int numero=Main.preguntaDevuelveInt();
        return new Contacto(nombre,apellidos,numero);
    }

    public void mostrarContacto() {
        for (Contacto c : this.listaContactos){
            System.out.println(c);
        }
    }
    public void eliminarContacto() {
       String nombreBuscado = Main.preguntaDevuelveString("Introduce el nombre del contacto a eliminar.");
       int indiceParaRemover = busquedaContactosArrayList(nombreBuscado);
       this.listaContactos.remove(indiceParaRemover);
    }

    public int busquedaContactosArrayList(String nombreBuscado){

        for (int i = 0; i <this.listaContactos.size()-1 ; i++) {
            if(this.listaContactos.get(i).getNombre().equalsIgnoreCase(nombreBuscado)){
                return i;
            }
        }
        return -10;
    }

    public void ordenarListaContactos(){
        Contacto c ;
        int comparador=1;

        while (comparador>0){
            for (int i = 0; i < this.listaContactos.size()-2; i++) {
                comparador= this.listaContactos.get(i).getNombre().compareToIgnoreCase(this.listaContactos.get(i+1).getNombre());
                if (comparador>0) {
                    c=this.listaContactos.get(i+1);
                    this.listaContactos.set(i+1,this.listaContactos.get(i));
                    this.listaContactos.set(i,c);
                }
            }
        }


// TODO basandome en lo que encontre aqui https://es.stackoverflow.com/questions/36804/m%C3%A9todo-sort-para-ordenar-el-arraylist-en-java
//this.listaContactos.sort((contacto1,contacto2)->(contacto1.getNombre().compareToIgnoreCase(contacto2.getNombre())));
    }
}
