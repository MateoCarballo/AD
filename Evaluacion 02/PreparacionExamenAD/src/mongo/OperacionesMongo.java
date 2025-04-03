package mongo;

import com.mongodb.MongoClientURI;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import java.util.Arrays;

public class OperacionesMongo {
    MongoClient mongoClient;
    MongoDatabase databaseLibro;
    MongoCollection<Document> coleccionLibros;
    MongoDatabase databaseLiga;
    MongoCollection<Document> coleccionEquipos;
    MongoDatabase databaseAndres;
    MongoCollection<Document> coleccionAndresCarrito;

    public OperacionesMongo() {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        databaseLibro = mongoClient.getDatabase("libros");
        coleccionLibros = databaseLibro.getCollection("libros");
        databaseLiga = mongoClient.getDatabase("liga");
        coleccionEquipos = databaseLiga.getCollection("equipos");
        databaseAndres = mongoClient.getDatabase("comercioAndres");
        coleccionAndresCarrito = databaseAndres.getCollection("Carrito");
    }

    public void punto4A() {
        // Resta 5 al precio de los libros cuya editorial sea Garceta
        Document filtroBusqueda = new Document("editorial", "Garceta");
        Document modificacionAplicada = new Document("$inc", new Document("pvp", -5));
        coleccionLibros.updateMany(filtroBusqueda, modificacionAplicada);
    }

    public void punto4B() {
        // Resta 5 al precio de los libros cuya editorial sea Garceta
        coleccionLibros.updateMany(new Document("editorial", "Garceta"), new Document("$inc", new Document("pvp", -5)));
    }

    public void punto5() {
        AggregateIterable<Document> resultado = coleccionLibros.aggregate(
                Arrays.asList(new Document("$match",
                                new Document("editorial", "Garceta")),
                        new Document("$match",
                                new Document("temas",
                                        new Document("$in", Arrays.asList("UML", "Neodatis")))),
                        new Document("$match",
                                new Document("pvp",
                                        new Document("$gte", 25L))))
        );

        for (Document doc : resultado) {
            System.out.println(doc.toString());
        }
    }

    public void liga5(String nombreEquipo) {
        var resultado = coleccionEquipos.aggregate(
                Arrays.asList(new Document("$match",
                        new Document("ciudad", nombreEquipo)))
        );

        for (Document doc : resultado) {
            System.out.println(doc.toString());
        }
    }

    public void liga6(String idJugador) {
        var resultado = coleccionEquipos.find(Filters.eq("jugadores.0.idPersonal", idJugador));

        for (Document doc : resultado) {
            System.out.println(doc.toString());
        }
    }

    public void andres(int idVideojuego, double precio) {

        coleccionAndresCarrito.updateOne(
                new Document("videojuegos.videojuego_id", idVideojuego), // Filtra el documento que contiene el videojuego con idVideojuego
                new Document("$set", new Document("videojuegos.$[elem].precio", precio)),  // Modifica el precio del videojuego dentro del array
                new UpdateOptions().arrayFilters(Arrays.asList(new Document("elem.videojuego_id", idVideojuego)))  // Filtro para asegurar que solo el videojuego con idVideojuego sea modificado
        );

/*

        coleccionAndresCarrito.aggregate(
                Arrays.asList(new Document("$unwind",
                                new Document("path", "$videojuegos")),
                        new Document("$match",
                                new Document("videojuegos.videojuego_id", 11L)),
                        new Document("$set",
                                new Document("videojuegos.precio", 1000L)),
                        new Document("$group",
                                new Document("_id", "$_id")
                                        .append("carrito_id",
                                                new Document("$first", "$carrito_id"))
                                        .append("usuario_id",
                                                new Document("$first", "$usuario_id"))
                                        .append("videojuegos",
                                                new Document("$push", "$videojuegos"))),
                        new Document("$merge",
                                new Document("db", "comercioAndres")
                                        .append("coll", "Carrito")
                                        .append("whenMatched", "merge")
                                        .append("whenNotMatched", "discard")))
        );
 */

        var afterConsulta = coleccionAndresCarrito.find();

        for (Document doc : afterConsulta) {
            System.out.println(doc.toString());
        }
    }

    public void apartado3() {
        MongoClient mongoClient1 = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase dbEmpresa = mongoClient1.getDatabase("empresa");
        MongoCollection empleados = dbEmpresa.getCollection("empleados");

        AggregateIterable<Document> consulta1 = empleados.aggregate(Arrays.asList(new Document("$sort",
                new Document("salario", -1L))));
        for (Document d: consulta1){
            System.out.println(d.toString());
        }

    }

    public void apartado4() {
        MongoClient mongoClient1 = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase dbEmpresa = mongoClient1.getDatabase("empresa");
        MongoCollection empleados = dbEmpresa.getCollection("empleados");

        AggregateIterable<Document> consulta1 = empleados.aggregate(
                Arrays.asList(new Document("$match",
                                new Document("departamento",
                                        new Document("$in", Arrays.asList(10L, 20L)))),
                        new Document("$project",
                                new Document("_id", 0L)
                                        .append("nombre", 1L)
                                        .append("departamento", 1L)))
        );
        for (Document d: consulta1){
            System.out.println(d.toString());
        }

    }


}

