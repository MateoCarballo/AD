import com.mongodb.client.*;
import com.mongodb.client.model.*;
import org.basex.examples.api.BaseXClient;
import org.bson.Document;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;

import static com.mongodb.client.model.Sorts.descending;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static BaseXClient session;
    private static MongoDatabase mongoDatabase;
    private static User userSelected = new User();
    /*
    Esto hace que los resutlados se muestren de una mejor forma

    private static JsonWriterSettings prettyPrintSettings = JsonWriterSettings.builder()
            .outputMode(JsonMode.STRICT)
            .indent(true)
            .build();

     Ejemplo de uso para printear de una forma mas bonita los resutlados
     System.out.println(document.toJson(prettyPrintSettings));

     */


    public static void main(String[] args) {
        //Creamos la conexiones con BaseX y MongoDB
        session = ConexionBaseX.getSession();
        mongoDatabase = ConexionMongo.getDataBase(ConexionMongo.DATABASE_NAME);
        ejecutarMenuPrincipal();

    }

    // Menu principal para elegir la tecnologia
    private static void ejecutarMenuPrincipal() {
        boolean continuar = true;
        while (continuar) {
            int opcion = elegirOpcion(StringResources.MENU_ELIGIR_TECNOLOGIA, 1, 3);
            switch (opcion) {
                case 1 -> menuOperacionesBaseX();
                case 2 -> menuOperacionesMongoDB();
                case 3 -> continuar = false;
            }
        }
        System.out.println("""
                Cerrando APP ... 
                """);
    }

    // ############################################ OPERACIONES SOBRE BASE X ############################################
    private static void menuOperacionesBaseX() {

        int opcion = elegirOpcion(StringResources.MENU_OPCIONES_BASEX, 1, 8);

        switch (opcion) {
            case 1 -> ejecutarConsultaBaseX(preguntarFiltroParaModificarPorId());
            case 2 -> ejecutarConsultaBaseX(preguntarFiltroParaEliminarPorId());
            case 3 -> ejecutarConsultaBaseX(StringResources.QUERY_1);
            case 4 -> ejecutarConsultaBaseX(pregunarFiltroParaConsultaQuery2());
            case 5 -> ejecutarConsultaBaseX(StringResources.QUERY_3);
            case 6 -> ejecutarConsultaBaseX(preguntarFiltroParaConsultaQuery4());
            case 7 -> ejecutarConsultaBaseX(StringResources.QUERY_5);
            case 8 -> ejecutarConsultaBaseX(StringResources.QUERY_6);
        }
    }

    private static String preguntarFiltroParaModificarPorId() {

        System.out.println("Introduce el id del juego a modificar");
        int numeroEtiqueta = -1;
        String nodeToChange = "";

        int idVideojuego = preguntarId();

        numeroEtiqueta = elegirOpcion(StringResources.MENU_ETIQUETAS_MODIFICABLES, 1, 8);

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
            return StringResources.QUERY_MODIFICAR_NODO_DOUBLE_POR_ID.formatted(idVideojuego, nodeToChange, newValueDotStyle);
        }
        if (numeroEtiqueta == 4 || numeroEtiqueta == 7) {
            return StringResources.QUERY_MODIFICAR_NODO_INT_POR_ID.formatted(idVideojuego, nodeToChange, Integer.parseInt(newValue));
        }
        return StringResources.QUERY_MODIFICAR_NODO_STRING_POR_ID.formatted(idVideojuego, nodeToChange, newValue);
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
        return StringResources.QUERY_ELIMINAR_POR_ID.formatted(idVideojuego);
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
        return StringResources.QUERY_2.formatted(filtroConsulta);
    }

    private static String preguntarFiltroParaConsultaQuery4() {
        System.out.println("Buscar en la descripción que contenga la string que introduces");
        String filtroConsulta = sc.nextLine();
        return StringResources.QUERY_4.formatted(filtroConsulta);
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

    // ############################################ OPERACIONES SOBRE MONGODB ############################################
    private static void menuOperacionesMongoDB() {
        try {
            System.out.println("Obtenida la conexion con la database en Mongo: " + ConexionMongo.DATABASE_NAME);
        } catch (Exception e) {
            System.out.println("Error al abrir la base de datos" + ConexionMongo.DATABASE_NAME + "\n" + e.getMessage());
            return;
        }

        int opcion = elegirOpcion(StringResources.MENU_OPCIONES_MONGO, 9, 18);

        switch (opcion) {
            case 9 -> insertarNuevoUsuario();
            case 10 -> seleccionarUsuarioPorEmail();
            case 11 -> eliminarUsuarioPorId();
            case 12 -> modificarCampoUsuarioSeleccionado();
//            case 13 ->
//            case 14 ->
//            case 15 ->
//            case 16 ->
            case 17 -> consulta17();
//            case 18 ->
        }
    }

    private static void insertarNuevoUsuario() {
        /*
        Consulta 1: Teniendo en cuenta todos los usuarios,
        calcular el coste de cada carrito y listar los resultados
        ordenados por el total de forma descendente.
         */
        User userToInsert = preguntarFiltroParaCrearUsuario();
        if (userToInsert == null) {
            System.out.println("Los datos recopilados no han podido crear un usuario, revisa los datos y repite la operacion");
            return;
        }

        MongoCollection<Document> usersColection = mongoDatabase.getCollection(ConexionMongo.COLLECTION_USERS_NAME);
        int newId = obtenerMayorUserId(ConexionMongo.COLLECTION_USERS_NAME);
        Document newUser = new Document("user_Id", newId)
                .append("name", userToInsert.getName())
                .append("email", userToInsert.getEmail())
                .append("age", userToInsert.getAge())
                .append("direction", userToInsert.getDirection());
        usersColection.insertOne(newUser);
        System.out.println("Usuario \"" + userToInsert.getName() + "\"  añadido con exito!");
    }

    private static User preguntarFiltroParaCrearUsuario() {
        /*
        {
        "user_Id": 1,
        "name": "José López",
        "email": "joselopez@example.com",
        "age": 28,
        "direction": "Calle Príncipe, 36202 Vigo, Pontevedra, España"
        },
         */

        /*  1. Consultar cual es el id mas alto y sumarle 1.
            2. Preguntar nombre.
            3. Preguntar email.
            4. Edad.
            5. Direccion.
        */
        String name = "";
        String email = "";
        int age = -1;
        String direction = "";

        boolean datoValido = true;

        System.out.println("Nombre del nuevo usuario");
        name = sc.nextLine();

        do {
            System.out.println("E-mail del nuevo usuario (correoEjemplo@dominio.es)");
            email = sc.nextLine();
            //Si existe un usuario que tenga el email que introducimos por teclado devuelve true
        } while (comprobarCampoRepetido(ConexionMongo.COLLECTION_USERS_NAME, "email", email));

        do {
            try {
                System.out.println("Edad del nuevo usuario");
                age = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debes introducir un numero " + e.getMessage());
                datoValido = false;
                sc.nextLine();
            }
        } while (!datoValido);

        System.out.println("Direccion del nuevo usuario");
        direction = sc.nextLine();

        return new User(name, email, age, direction);

    }

    private static int obtenerMayorUserId(String collectionName) {
        int maxUserId = 0;
        MongoCollection<Document> usersColection = mongoDatabase.getCollection(collectionName);
        Document highestUser = usersColection.find()
                .sort(descending("user_Id"))
                .first();
        if (highestUser != null) {
            maxUserId = highestUser.getInteger("user_Id");
        } else {
            System.out.println("No se encontraron usuarios.");
        }
        return maxUserId + 1;
    }

    private static boolean comprobarCampoRepetido(String collectionName, String campo, String valor) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        Document foundUser = collection.find(Filters.eq(campo, valor)).first();
        if (foundUser != null) {
            System.out.println("Este correo ya está registrado");
            return true;
        }
        return false;
    }

    private static void consulta17() {
        /*
        Consulta 1: Teniendo en cuenta todos los usuarios, calcular el coste de cada carrito
        y listar los resultados ordenados por el total de forma descendente.
         */
        AggregateIterable<Document> iterDoc = mongoDatabase
                .getCollection(ConexionMongo.COLLECTION_SHOPPING_CARTS_NAME)
                .aggregate(
                        Arrays.asList(
                                Aggregates.unwind("$items"),
                                Aggregates.addFields(
                                        new Field<>("totalItemCost",
                                                new Document("$multiply", Arrays.asList(
                                                        "$items.quantity", "$items.price"
                                                ))
                                        )
                                ),
                                Aggregates.lookup("Usuarios", "user_Id", "user_Id", "userInfo"),
                                Aggregates.group("$user_Id",
                                        Accumulators.sum("totalCost", "$totalItemCost")
                                ),
                                Aggregates.sort(Sorts.descending("totalCost")))
                );
        for (Document document : iterDoc) {
            System.out.println(document);
        }
    }

    public static void seleccionarUsuarioPorEmail() {
        MongoCollection<Document> colectionUsuarios = mongoDatabase.getCollection(ConexionMongo.COLLECTION_USERS_NAME);
        String email = preguntarFiltroParaObtenerUsuario();
        Document usuario = colectionUsuarios.find(Filters.eq("email", email)).first();
        if (usuario != null) {
            int userId = usuario.getInteger("user_Id");
            String name = usuario.getString("name");
            email = usuario.getString("email");
            int age = usuario.getInteger("age");
            String direction = usuario.getString("direction");
            userSelected.setUserId(userId);
            userSelected.setName(name);
            userSelected.setEmail(email);
            userSelected.setAge(age);
            userSelected.setDirection(direction);
        }
        System.out.println("Has selecccionado al usuario \n" + userSelected);
    }

    public static HashMap<Integer, String> obtenerUsuarios(){
        HashMap<Integer, String> paresEmail = new HashMap<>();
        FindIterable<Document> usersColection = mongoDatabase
                .getCollection(ConexionMongo.COLLECTION_USERS_NAME)
                .find()
                .projection(new Document("user_Id", 1).append("email",1))
                .sort(Sorts.ascending("user_Id"));

        System.out.println("Opciones disponibles");
        for (Document document : usersColection) {
            int userId = document.getInteger("user_Id");
            String email =document.getString("email");
            paresEmail.put(userId,email);
            System.out.println(document.getInteger("user_Id") + " - " + document.getString("email"));
        }
        return paresEmail;
    }

    public static void mostrarUsuarios(HashMap<Integer,String> users){
        System.out.println("Usuarios disponibles");
        obtenerUsuarios().forEach((id,email) -> System.out.println(id + " " + email));
    }

    private static String preguntarFiltroParaObtenerUsuario() {
        HashMap<Integer, String> paresEmail = obtenerUsuarios();

        while(true){
            System.out.println("Elige una opcion, puedes indicar el id o el email");
            String entradaTeclado = sc.nextLine();
            try{
                int id = Integer.parseInt(entradaTeclado);
                if (paresEmail.containsKey(id)){
                    return paresEmail.get(id);
                }
            } catch (NumberFormatException e) {
                if (paresEmail.containsValue(entradaTeclado)){
                    for (Map.Entry<Integer,String> parClaveValor : paresEmail.entrySet()){
                        if (parClaveValor.getValue().equals(entradaTeclado)) {
                            return parClaveValor.getValue();
                        }
                    }
                }
            }
        }
    }

    public static void eliminarUsuarioPorId(){
        HashMap<Integer, String> paresEmail = obtenerUsuarios();
        MongoCollection usersCollection = mongoDatabase.getCollection(ConexionMongo.COLLECTION_USERS_NAME);
        MongoCollection cartCollection = mongoDatabase.getCollection(ConexionMongo.COLLECTION_SHOPPING_CARTS_NAME);
        MongoCollection purchasesCollection = mongoDatabase.getCollection(ConexionMongo.COLLECTION_PURCHASES_NAME);
        System.out.println("Escribe el correo que deseas eliminar");
        String entradaTeclado = sc.nextLine();
        try{
            int id = Integer.parseInt(entradaTeclado);
            if (paresEmail.containsKey(id)){
                usersCollection.deleteOne(Filters.eq("user_Id",id));
                cartCollection.deleteOne(Filters.eq("user_Id",id));
                purchasesCollection.deleteMany(Filters.eq("user_Id",id));
            }
        } catch (NumberFormatException e) {
            if (paresEmail.containsValue(entradaTeclado)){
                for (Map.Entry<Integer,String> parClaveValor : paresEmail.entrySet()){
                    if (parClaveValor.getValue().equals(entradaTeclado)) {
                        usersCollection.deleteOne(Filters.eq("user_Id",parClaveValor.getKey()));
                        cartCollection.deleteOne(Filters.eq("user_Id",parClaveValor.getKey()));
                        purchasesCollection.deleteMany(Filters.eq("user_Id",parClaveValor.getKey()));
                    }
                }
            }
        }
    }

    public static void modificarCampoUsuarioSeleccionado(){
        if (userSelected.getName() == null){
            System.out.println("Debes seleccionar un usuario en el punto 10 para poder modificar sus campos");
            return;
        }
        System.out.println(StringResources.MENU_CLAVE_MODIFICABLES_USUARIOS.formatted(userSelected.getName()));
        int entradaTeclado = -1;
        String name = "";
        int age = 0;
        String email = "";
        String direction = "";

        do{
            entradaTeclado = elegirOpcion(StringResources.MENU_CLAVE_MODIFICABLES_USUARIOS.formatted(userSelected.getName()),1,4);
            switch (entradaTeclado){
                case 1 ->{
                    name = sc.nextLine();
                    actualizarCampoString(ConexionMongo.FIELD_NAME,name);
                }
                case 2 ->{
                    email = sc.nextLine();
                    actualizarCampoString(ConexionMongo.FIELD_EMAIL,email);
                }
                case 3 ->{
                    age = sc.nextLine();
                    actualizarCampoInt(ConexionMongo.FIELD_AGE,age);
                }
                case 4 ->{
                    direction = sc.nextLine();
                    actualizarCampoString(ConexionMongo.FIELD_DIRECTION,direction);
                }
            }
        }while(entradaTeclado != 0);

    }

    public static void actualizarCampoString(String fieldName, String value){
        MongoCollection<Document> userCollection = mongoDatabase.getCollection(ConexionMongo.COLLECTION_USERS_NAME);
        userCollection.updateOne(
                Filters.eq(ConexionMongo.FIELD_NAME,userSelected.getName()),
                Updates.set(fieldName,value)
        );
    }

    public static void actualizarCampoInt(String fieldName, int value){
        MongoCollection<Document> userCollection = mongoDatabase.getCollection(ConexionMongo.COLLECTION_USERS_NAME);
        userCollection.updateOne(
                Filters.eq(ConexionMongo.FIELD_NAME,userSelected.getName()),
                Updates.set(fieldName,value)
        );

    }

    // ############################################ OPERACIONES GLOBALES ############################################

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
}