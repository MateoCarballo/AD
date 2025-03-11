import org.basex.examples.api.BaseXClient;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static final String HOST = "localhost";
    private static final int PORT = 1984;
    private static final String USER = "admin";
    private static final String PWD = "abc123";

    private static final String BASEX_DATABASE_NAME = "videojuegos";
    private static BaseXClient session;

    private static final String QUERY_1 = """
            for $videojuego in /videojuegos/videojuego
            order by $videojuego/plataforma, $videojuego/titulo
            return <videojuego>{$videojuego/id}{$videojuego/titulo}{$videojuego/precio}{$videojuego/disponibilidad}{$videojuego/edad_minima_recomendada}{$videojuego/plataforma}
            </videojuego>
            """;
    // Esta QUERY_2 seria la version generica como la pido por teclado la personalizo en el metodo desde el eque ejecuto la consilta con la entrada de teclado
    private static String QUERY_2 = """
            for $videojuego in /videojuegos/videojuego
            where $videojuego/edad_minima_recomendada < 12
            order by $videojuego/edad_minima_recomendada
            return <videojuego>{$videojuego/id}{$videojuego/titulo}{$videojuego/precio}{$videojuego/disponibilidad}{$videojuego/edad_minima_recomendada}{$videojuego/plataforma}
            </videojuego>
            """;
    private static final String QUERY_3 = """
            for $plataforma in distinct-values(/videojuegos/videojuego/plataforma)
            let $precio :=  min(/videojuegos/videojuego[plataforma = $plataforma]/precio)
            let $titulo := /videojuegos/videojuego[precio = $precio]/titulo
            return concat("Plataforma: ",$plataforma," Titulo: ",$titulo," Precio: ",$precio)
            """;
    private static final String QUERY_4 = """
            """;
    private static final String QUERY_5 = """
            """;
    private static final String QUERY_6 = """
            """;
    private static final String QUERY_7 = """
            """;

    public static void main(String[] args) {
        try{
            session = new BaseXClient(HOST,PORT,USER,PWD);
            System.out.println("Conexion creada con BaseX");
        } catch (IOException e) {
            System.out.println("Error al crear la conexion con la base de datos en BaseX");
            e.printStackTrace();
        }

        int opcion;
        Boolean continuar = true;
        // Repeticiones para poder realizar varias consultas
        do{
        opcion = primerMenu();
        if (opcion == 1){
            menuOperacionesBaseX(session);
        }
        if(opcion == 2) {
            menuOperacionesMongoDB();
        }else{
            //TODO quizá aqui se necesite algo mas currado pero por ahora vale
            continuar = false;
        }

        }while(continuar);
    }


    private static int primerMenu(){
        int opcion = Integer.MIN_VALUE;
        do{
            try{
                System.out.println("""
                    1.Consultas sobre BaseX.
                    2.Consulta sobre MongoDB.
                    """);
                opcion = sc.nextInt();
            }catch (Exception e){
                System.out.println("Error al seleccionar la opcion");
                e.printStackTrace();
            }
        }while((opcion != 1) && (opcion != 2));
        return opcion;
    }

    private static void menuOperacionesBaseX(BaseXClient session){
        //TODO pendiente de abrir la base de datos que usaremos para asegurarnos que estemos,
        // conectados y simplificar las consultas.
        try {
            session.execute("open " + BASEX_DATABASE_NAME);
        } catch (IOException e) {
            System.out.println("Error al abrir la base de datos");
        }
        System.out.println("""
                                       ######### OPERACIONES SOBRE BASEX ##########
                                       
                                       ######### OPERACIONES DE MODIFICACION Y ELIMINACION ##########
                
                1. Modificar elemento por 'id'.
                
                2. Eliminar un videojuego según su ID.
                
                                       ######### CONSULTAS ##########
                
                3. Consulta 1: Obtener todos los videojuegos ordenados por plataforma y 
                   en segundo lugar por título (se mostrarán los siguientes campos: 
                   id, titulo, precio, disponibilidad, edad_minima_recomendada y plataforma).
                
                4. Consulta 2: Listar videojuegos con una edad_minima_recomendada menor o 
                   igual a X años (se mostrarán los siguientes campos: id, titulo, precio, 
                   disponibilidad, edad_minima_recomendada y plataforma). Se deberá mostrar 
                   la información ordenada según la edad_minima_recomendada.
                
                5. Consulta 3: Mostrar la plataforma, el titulo y el precio del videojuego
                   más barato para cada plataforma. En el caso de haber varios se devolverá 
                   el de la primera posición.
                 
                6. Consulta 4: Mostrar el titulo y el genero de aquellos videojuegos cuya
                   descripcion incluya una subcadena, independientemente del uso de mayúsculas
                   o minúsculas. Se deberá mostrar la información ordenada alfabéticamente
                   según el genero.
                 
                7. Consulta 5: Mostrar la cantidad total de videojuegos para cada plataforma
                   (teniendo en cuenta el elemento disponibilidad) y calcular el porcentaje que
                   representa respecto al total de videojuegos. Se deberá mostrar la información
                   ordenada de forma descendente por la cantidad de videojuegos.
                   
                8. Consulta 6: Mostrar el precio que costaría comprar todos los videojuegos disponibles 
                   (teniendo en cuenta el precio de cada videojuego y la disponibilidad de cada uno).
                """);
        try{
            int opcion = sc.nextInt();
            switch (opcion){
                case 1 -> modificarElementoXmlPorId(session);
                case 2-> eliminarPorId(session);
                case 3-> ejecutarConsultaBaseX(session,QUERY_1);
                case 4-> pregunarFiltroParaConsulta();
                case 5-> ejecutarConsultaBaseX(session,QUERY_3);
                case 6-> ejecutarConsultaBaseX(session,QUERY_4);
                case 7-> ejecutarConsultaBaseX(session,QUERY_5);
                case 8-> ejecutarConsultaBaseX(session,QUERY_6);
                default -> System.out.println("Opcion fuera de rango");
            }
        }catch (Exception e){
            System.out.println("Error al seleccionar una opcion");
            e.printStackTrace();
        }


    }

    private static void pregunarFiltroParaConsulta() {
        System.out.println("Edad minima sobre la que filtraremos la DB");
        String filtroConsulta = sc.next();
        StringBuilder query2 = new StringBuilder();
        query2.append("for $videojuego in /videojuegos/videojuego \n")
                .append("where $videojuego/edad_minima_recomendada < " + filtroConsulta + "\n")
                .append("order by $videojuego/edad_minima_recomendada")
                .append("return <videojuego>{$videojuego/id}{$videojuego/titulo}{$videojuego/precio}{$videojuego/disponibilidad}{$videojuego/edad_minima_recomendada}{$videojuego/plataforma}\n")
                .append("</videojuego>");
        ejecutarConsultaBaseX(session, query2.toString());
    }

    private static void modificarElementoXmlPorId(BaseXClient session) {
    }

    private static void eliminarPorId(BaseXClient session) {
    }

    private static void ejecutarConsultaBaseX(BaseXClient session, String queryAsString) {
        try {
            BaseXClient.Query query = session.query(queryAsString);
            while (query.more()){
                System.out.println(query.next());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    private static void menuOperacionesMongoDB(){
        System.out.println("""
                1. Crear un nuevo usuario (no podrá haber email repetidos).
                
                2. Identificar usuario según el email. Dado el email se obtendrá el id del usuario 
                   de forma que las siguientes consultas se harán sobre ese usuario. Para cambiar de 
                   usuario se tendrá que volver a seleccionar esta opción.
                
                3. Borrar un usuario.
                
                4. Modificar el valor de un campo de la información del usuario.
                
                5. Añadir videojuegos al carrito del usuario. Se mostrará la lista de videojuegos 
                   cuya edad_minima_recomendada sea inferior o igual a la del usuario actual y se pedirá:
                   id del videojuego y cantidad, así como si se desea seguir introduciendo más videojuegos.
                 
                6. Mostrar el carrito del usuario. Se mostrarán los datos del carrito y el coste total.
                
                7. Comprar el carrito del usuario. Se mostrará el contenido del carrito junto con una orden 
                   de confirmación. Si la orden es positiva se pasarán todos los videojuegos a formar parte de 
                   una nueva compra y desaparecerán del carrito.
                
                8. Mostrar las compras del usuario, incluyendo la información de la fecha de cada compra.
                
                9. Consulta 1: Teniendo en cuenta todos los usuarios, calcular el coste de cada carrito y 
                   listar los resultados ordenados por el total de forma descendente.
                
                10.Consulta 2: Teniendo en cuenta todos los usuarios, calcular el total gastado por cada usuario
                 en todas sus compras y listar los resultados ordenados por el total de forma ascendente.
                """);

    }

}