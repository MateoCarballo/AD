package Ejercicios_Postgre.Ejercicio503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

/*
Ejercicio 503
Usando Java se pide realizar las siguientes funcionalidades usando como referencia la siguiente base de datos sobre pokemons

Las funciones a implementar será aquellas que permitan:

Insertar un nuevo pokemon.
Modificar un pokemon.
Eliminar un pokemon.
*/

public class Ejercicio503 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        //probarConexion();
        lanzarMenu();
    }

    private static void lanzarMenu() {
        boolean continuar = true;
        while (continuar) {
            // Imprimir el menú en consola
            System.out.println("""
                    ========================
                    Menú de Gestión de Pokémon
                    ========================
                    1. Insertar Pokémon
                    2. Modificar Pokémon
                    3. Eliminar Pokémon
                    4. Salir
                    ========================
                    Elige una opción:
                    """);

            // Leer la opción del usuario
            int opcion = 0;
            try {
                opcion = Integer.parseInt(br.readLine());
            } catch (IOException e) {
                System.out.println("Error al leer entrada para menu -> " + e.getMessage());
            }

            // Manejar las opciones del menú
            switch (opcion) {
                case 1 -> insertarPokemon();
                case 2 -> modificarPokemon();
                case 3 -> eliminarPokemon();
                case 4 -> {
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    continuar = false;
                }
                default -> System.out.println("Opción no válida. Por favor, intenta de nuevo.");
            }
        }
    }

    private static void insertarPokemon() {
        try {
            System.out.println("Introduce el nombre para el nuevo pokemon");
            String nombre = br.readLine();
            System.out.println("Introduce el tipo para el nuevo pokemon");
            String tipo = br.readLine();
            System.out.println("Introduce el nivel para el nuevo pokemon");
            String nivel = br.readLine();
        } catch (IOException e) {
            System.out.println("Error al leer datos del teclado -> " + e.getMessage());
        }
    }

    private static void modificarPokemon() {
        // Aquí puedes agregar la lógica para modificar un Pokémon
    }

    private static void eliminarPokemon() {
        // Aquí puedes agregar la lógica para eliminar un Pokémon
    }

    private static void probarConexion() {
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Pokemon", "postgres", "abc123.")) {
            try (PreparedStatement preparedStatement = conn.prepareStatement(
                    "SELECT id, (pokemon).nombre, (pokemon).tipo, (pokemon).nivel FROM objetos.Pokemons")) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt(1);
                        String nombre = resultSet.getString(2);
                        String tipo = resultSet.getString(3);
                        int nivel = resultSet.getInt(4);
                        System.out.println("ID -> " + id + "\n" +
                                "Nombre -> " + nombre + "\n" +
                                "Tipo -> " + tipo + "\n" +
                                "Nivel -> " + nivel + "\n");
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Error al crear la consulta -> " + ex.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error al conectarse a la database pokemons -> " + e.getMessage());
        }
    }
}
