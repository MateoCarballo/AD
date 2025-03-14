import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.basex.examples.api.BaseXClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static BaseXClient session;

    private static final String BASE_X_HOST = "localhost";
    private static final int PORT = 1984;
    private static final String USER = "admin";
    private static final String PWD = "abc123";

    private static MongoClient mongoClient;
    private static String MONGO_DB_HOST = "mongodb://localhost:27017";


    private static final String MENU_ELIGIR_TECNOLOGIA = """
            Seleccione una opción:
            1. Operaciones BaseX
            2. Operaciones MongoDB
            3. Salir""";

    private static final String MENU_OPCIONES_BASEX = """
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
            """;

    private static final String MENU_OPCIONES_MONGO = """
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
            """;

    private static final String MENU_ETIQUETAS_MODIFICABLES = """
            Que quieres modificar del videojuego:
            1. Titulo.
            2. Descripcion.
            3. Precio.
            4. Disponibilidad.
            5. Genero.
            6. Desarrollador.
            7. Edad minima recomendada.
            8. Plataforma.
            """;
    private static final String BASEX_DATABASE_NAME = "videojuegos";

    private static final String QUERY_MODIFICAR_NODO_STRING_POR_ID = """
            for $videojuego in /videojuegos/videojuego[id = %d]
            return replace value of node $videojuego/%s with "%s"
            """;
    private static final String QUERY_MODIFICAR_NODO_INT_POR_ID = """
            for $videojuego in /videojuegos/videojuego[id = %d]
            return replace value of node $videojuego/%s with "%d"
            """;
    private static final String QUERY_MODIFICAR_NODO_DOUBLE_POR_ID = """
            for $videojuego in /videojuegos/videojuego[id = %d]
            return replace value of node $videojuego/%s with '%s'
            """;

    private static final String QUERY_ELIMINAR_POR_ID = """
            for $videojuego in /videojuegos/videojuego[id = %d]
            return delete node $videojuego
            """;

    private static final String QUERY_1 = """
            for $videojuego in /videojuegos/videojuego
            order by $videojuego/plataforma, $videojuego/titulo
            return <videojuego>{$videojuego/id}{$videojuego/titulo}{$videojuego/precio}{$videojuego/disponibilidad}{$videojuego/edad_minima_recomendada}{$videojuego/plataforma}
            </videojuego>
            """;

    private static String QUERY_2 = """
            for $videojuego in /videojuegos/videojuego
            where $videojuego/edad_minima_recomendada < %d
            order by $videojuego/edad_minima_recomendada
            return <videojuego>{$videojuego/id}{$videojuego/titulo}{$videojuego/precio}{$videojuego/disponibilidad}{$videojuego/edad_minima_recomendada}{$videojuego/plataforma}</videojuego>
            """;
    private static final String QUERY_3 = """
            for $plataforma in distinct-values(/videojuegos/videojuego/plataforma)
            let $precio :=  min(/videojuegos/videojuego[plataforma = $plataforma]/precio)
            let $titulo := /videojuegos/videojuego[precio = $precio]/titulo
            return concat("Plataforma: ",$plataforma," Titulo: ",$titulo[1]," Precio: ",$precio[1])
            """;
    /*
    OTRA forma de realizar la consulta 3
   for $plataforma in distinct-values(/videojuegos/videojuego/plataforma)
            let $precio :=  min(/videojuegos/videojuego[plataforma = $plataforma]/precio)
            let $titulo := head(/videojuegos/videojuego[precio = $precio]/titulo)
            return concat("Plataforma: ",$plataforma," Titulo: ",$titulo," Precio: ",$precio)
     */
    private static final String QUERY_4 = """
            for $videojuego in /videojuegos/videojuego
            where contains($videojuego/descripcion,"%s")
            return concat("Titulo: ",$videojuego/titulo, " Genero: ", $videojuego/genero)
            """;

    private static final String QUERY_5 = """
            let $total_videojuegos := sum(//disponibilidad)
            for $plataforma in distinct-values(/videojuegos/videojuego/plataforma)
            for $suma_videojuego in sum(/videojuegos/videojuego[plataforma = $plataforma]/disponibilidad)
            let $porcent := round(($suma_videojuego div $total_videojuegos) * 100,2)
            order by count(/videojuegos/videojuego[plataforma = $plataforma]/disponibilidad) descending
            return  concat("Suma plataforma: ",$plataforma," es igual a ",$suma_videojuego," lo que representa un porcentaje del ",$porcent," %")
            """;

    private static final String QUERY_6 = """
            let $precioTodosVideojuegos := sum(
              for $videojuego in /videojuegos/videojuego
              let $precioUnidad := $videojuego/precio
              let $unidades := $videojuego/disponibilidad
              return $precioUnidad * $unidades)
            return concat("El precio total de comprar todos los videojuegos es ",format-number($precioTodosVideojuegos, '#0.00'))
            """;

    public static void main(String[] args) {
        try (BaseXClient session = new BaseXClient(BASE_X_HOST, PORT, USER, PWD)) {
            Main.session = session;
            if (Main.session != null) {
                System.out.println("Conexión establecida con BaseX.");
            }
        } catch (IOException e) {
            System.out.println("Error al conectar con BaseX: " + e.getMessage());
            System.exit(1);
        }

        try (MongoClient mongoClient = MongoClients.create(MONGO_DB_HOST)) {
            Main.mongoClient = mongoClient;
            if (Main.mongoClient != null) {
                System.out.println("Conexión establecida con BaseX.");
            }
        } catch (Exception e) {
            System.out.println("Error al conectar con Mongo: " + e.getMessage());
            System.exit(1);
        }
        ejecutarMenuPrincipal();

    }

    private static void ejecutarMenuPrincipal() {
        boolean continuar = true;
        while (continuar) {
            int opcion = elegirOpcion(MENU_ELIGIR_TECNOLOGIA, 1, 3);
            switch (opcion) {
                case 1 -> menuOperacionesBaseX();
                case 2 -> menuOperacionesMongoDB();
                case 3 -> continuar = false;
            }
        }
        System.out.println("¡Hasta pronto!");
    }

    private static int elegirOpcion(String menu, int min, int max) {
        int opcion = -1;
        do {
            try {
                System.out.println(menu);
                opcion = Integer.parseInt(sc.nextLine());
                if (opcion < min || opcion > max) {
                    System.out.println("ERROR: Opción fuera de rango");
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Entrada inválida, ingrese un número.");
            }
        } while (opcion < min || opcion > max);
        return opcion;
    }

    private static void menuOperacionesBaseX() {

        try {
            session.execute("open " + BASEX_DATABASE_NAME);
            System.out.println("Conectado con la base de datos " + BASEX_DATABASE_NAME);
        } catch (IOException e) {
            System.out.println("Error al abrir la base de datos \n" + e.getMessage());
            return;
        }

        int opcion = elegirOpcion(MENU_OPCIONES_BASEX, 1, 8);

        switch (opcion) {
            case 1 -> ejecutarConsultaBaseX(preguntarFiltroParaModificarPorId());
            case 2 -> ejecutarConsultaBaseX(preguntarFiltroParaEliminarPorId());
            case 3 -> ejecutarConsultaBaseX(QUERY_1);
            case 4 -> ejecutarConsultaBaseX(pregunarFiltroParaConsultaQuery2());
            case 5 -> ejecutarConsultaBaseX(QUERY_3);
            case 6 -> ejecutarConsultaBaseX(preguntarFiltroParaConsultaQuery4());
            case 7 -> ejecutarConsultaBaseX(QUERY_5);
            case 8 -> ejecutarConsultaBaseX(QUERY_6);
        }
    }

    private static String preguntarFiltroParaModificarPorId() {

        System.out.println("Introduce el id del juego a modificar");
        int numeroEtiqueta = -1;
        String nodeToChange = "";

        int idVideojuego = preguntarId();

        numeroEtiqueta = elegirOpcion(MENU_ETIQUETAS_MODIFICABLES, 1, 8);

        switch (numeroEtiqueta) {
            case 1 -> nodeToChange = "titulo";
            case 2 -> nodeToChange = "descripcion";
            case 3 -> nodeToChange = "precio";
            case 4 -> nodeToChange = "disponibilidad";
            case 5 -> nodeToChange = "genero";
            case 6 -> nodeToChange = "desarrollador";
            case 7 -> nodeToChange = "edad_minima_recomendada";
            case 8 -> nodeToChange = "plataforma";
        }

        System.out.println("Indica el nuevo valor para el nodo seleccionado (" + nodeToChange + ")");
        String newValue = sc.nextLine();
        if (numeroEtiqueta == 3) {
            newValue = newValue.replace(",", ".");
            double newValueDouble = Double.parseDouble(newValue);
            String newValueDotStyle = String.format(Locale.US, "%.2f", newValueDouble);
            return QUERY_MODIFICAR_NODO_DOUBLE_POR_ID.formatted(idVideojuego, nodeToChange, newValueDotStyle);
        }
        if (numeroEtiqueta == 4 || numeroEtiqueta == 7) {
            return QUERY_MODIFICAR_NODO_INT_POR_ID.formatted(idVideojuego, nodeToChange, Integer.parseInt(newValue));
        }
        return QUERY_MODIFICAR_NODO_STRING_POR_ID.formatted(idVideojuego, nodeToChange, newValue);
    }

    private static int preguntarId() {
        ArrayList<Integer> listadoIds = new ArrayList<>();
        int idVideojuego = -1;
        //Preguntar qué ids existen en la database
        try {
            BaseXClient.Query ids = session.query("distinct-values(/videojuegos/videojuego/id)");
            while (ids.more()) {
                listadoIds.add(Integer.parseInt(ids.next()));
            }
        } catch (IOException e) {
            System.out.println("Error al realizar la consulta");
        }

        //Imprimirlos por pantalla

        System.out.println("Ids disponibles en la database");
        System.out.print("[ ");
        for (int i : listadoIds) {
            System.out.print(i + ", ");
        }
        System.out.println("]");

        //Recoger la eleccion y comprobar que este contenido en los existentes si no volver a preguntar
        do {
            try {
                System.out.println("Introduce el id del producto");
                idVideojuego = Integer.parseInt(sc.nextLine());
                if (!listadoIds.contains(idVideojuego)) {
                    System.out.println("Escoge un id entre los disponibles !!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ha ocurrido un error revisa los datos");
            }
        } while (!listadoIds.contains(idVideojuego));
        return idVideojuego;
    }

    private static String preguntarFiltroParaEliminarPorId() {
        System.out.println("Introduce el id del videojuego que quieres eliminar");
        int idVideojuego = preguntarId();
        return QUERY_ELIMINAR_POR_ID.formatted(idVideojuego);
    }

    private static String pregunarFiltroParaConsultaQuery2() {
        int filtroConsulta = -1;
        do {
            try {
                System.out.println("Edad minima sobre la que filtraremos la DB");
                filtroConsulta = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debes introducir un numero " + e.getMessage());
                sc.nextLine();
            }
        } while (filtroConsulta == -1);
        return QUERY_2.formatted(filtroConsulta);
    }

    private static String preguntarFiltroParaConsultaQuery4() {
        System.out.println("Buscar en la descripción que contenga la string que introduces");
        String filtroConsulta = sc.nextLine();
        return QUERY_4.formatted(filtroConsulta);
    }


    private static void ejecutarConsultaBaseX(String queryAsString) {
        //TODO Jose, debo gestionar que si esta abierto por otro proceso el programa te repita la preguna o se puede cerrar la ejecucion
        try {
            BaseXClient.Query query = session.query(queryAsString);
            while (query.more()) {
                System.out.println(query.next());
                System.out.println(session.info());
            }
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error de tipo " + e.getMessage());
            ;
        } catch (RuntimeException e) {
            System.out.println("Revisa que BaseXGUI no este abierto y conectado a la misma base de datos " + e.getMessage());
        }
    }


    private static void menuOperacionesMongoDB() {
        int opcion = elegirOpcion(MENU_OPCIONES_MONGO, 1, 10);

        switch (opcion) {
            case 1 ->
            case 2 ->
            case 3 ->
            case 4 ->
            case 5 ->
            case 6 ->
            case 7 ->
            case 8 ->
            case 9 ->
            case 10 ->
        }
    }

}