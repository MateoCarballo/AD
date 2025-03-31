package baseX;

import model.Examen;
import org.basex.examples.api.BaseXClient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class OperacionesBaseX {
    public BaseXClient  session;

    public OperacionesBaseX() throws Exception {
        try {
            this.session= new BaseXClient("localhost", 1984, "admin", "abc123");
        } catch (IOException e) {
            throw new Exception("Error al conectar con BaseX\n" + e.getMessage());
        }
    }

    public void crearDatabaseBaseXArchivoUnico() throws Exception {
        try (BaseXClient session = new BaseXClient("localhost", 1984, "admin", "abc123")) {
            // Listar bases de datos existentes
            String basesExistentes = session.execute("LIST");
            System.out.println(basesExistentes);

            // Si la base de datos no existe, crearla
            if (!basesExistentes.contains("Examenes")) {
                session.execute("CREATE DATABASE Examenes");
                session.execute("ADD TO Examenes <examenes/>"); // Crear nodo raíz al inicio
            }
            session.execute("OPEN Examenes");

            // Verificar si la base de datos está vacía y agregar nodo raíz si es necesario
            String countDocs = session.execute("XQUERY count(/)"); // Esto es XQuery y se ejecuta con session.execute
            if (countDocs.equals("0")) {
                session.execute("ADD TO Examenes <examenes/>");
            }

            // Crear lista de exámenes
            ArrayList<Examen> examenes = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                examenes.add(new Examen("materia " + (1 + i), LocalDate.now(), 1 + i));
            }

            // node $datos/[colorsable= ] with nuevovaslor

            // Insertar cada examen dentro de <examenes>
            for (Examen e : examenes) {
                String formateadaXML = e.formatExamenToXML();
                BaseXClient.Query queryInsertarNodo = session.query("insert node " + formateadaXML + " into /examenes");
                queryInsertarNodo.execute();
                System.out.println(queryInsertarNodo.info());
            }

        } catch (Exception e) {
            throw new Exception("Excepción en el método crearDatabaseBaseXArchivoUnico\n" + e.getMessage());
        }
    }

    public void modificarExamenPorMateriaMetodoA(String materiaBuscada, String nuevaMateria) throws Exception {
        try (BaseXClient session = new BaseXClient("localhost", 1984, "admin", "abc123")) {
            // Usar la base de datos "Examenes"
            session.execute("OPEN Examenes");

            // Crear una consulta XQuery para buscar el nodo que contiene la materia que estamos buscando
            String queryBuscar = "XQUERY //examen[materia='" + materiaBuscada + "']"; // Busca el examen con la materia específica

            // Ejecutar la consulta de búsqueda
            String resultado = session.execute(queryBuscar);

            // Si se encuentra el examen (no está vacío)
            if (!resultado.isEmpty()) {
                // Crear una consulta para modificar el nodo encontrado
                String queryModificar = "XQUERY replace node //examen[materia='%s']/materia with <materia>%s</materia>".formatted(materiaBuscada, nuevaMateria);
                // Ejecutar la consulta para modificar el nombre de la materia
                session.execute(queryModificar);
                System.out.println("Examen modificado correctamente.");
            } else {
                System.out.println("No se encontró un examen con la materia: " + materiaBuscada);
            }
        } catch (Exception e) {
            throw new Exception("Excepción al modificar el examen por materia\n" + e.getMessage());
        }
    }

    public void modificarExamenPorMateriaMetodoB(String materiaBuscada, String nuevaMateria) throws Exception {
        try (BaseXClient session = new BaseXClient("localhost", 1984, "admin", "abc123")) {
            // Usar la base de datos "Examenes"
            session.execute("OPEN Examenes");

            // Crear una consulta XQuery para buscar el nodo que contiene la materia que estamos buscando
            BaseXClient.Query queryBuscar = session.query("//examen[materia='" + materiaBuscada + "']");

            // Si se encuentra el examen (no está vacío)
            if (queryBuscar.more()) {
                // Crear una consulta para modificar el nodo encontrado
                BaseXClient.Query queryModificar = session.query("replace node //examen[materia='%s']/materia with <materia>%s</materia>".formatted(materiaBuscada, nuevaMateria));
                // Ejecutar la consulta para modificar el nombre de la materia
                queryModificar.execute();

                System.out.println("Examen modificado correctamente.");
            } else {
                System.out.println("No se encontró un examen con la materia: " + materiaBuscada);
            }
        } catch (Exception e) {
            throw new Exception("Excepción al modificar el examen por materia B \n" + e.getMessage());
        }
    }


    public void crearDatabaseBaseXVariosArchivos() throws Exception {

        ArrayList<Examen> examenes = new ArrayList();
        for (int i = 0; i < 10; i++) {
            examenes.add(new Examen("materia " + i, LocalDate.now(), 7 + i));
        }
        InputStream bais = new ByteArrayInputStream(examenes.get(0).toString().getBytes());
        session.create("Examenes", bais);
        for (int i = 1; i < examenes.size(); i++) {
            bais = new ByteArrayInputStream(examenes.get(i).toString().getBytes());
            session.add("Examenes/examen" + i + ".xml", bais);
        }
    }

    private static String formatExamenToXML(Examen e) {
        return String.format(
                "<examen fecha=\"%s\">\n" +
                        "    <materia>%s</materia>\n" +
                        "    <horas-semanales>%d</horas-semanales>\n" +
                        "</examen>\n",
                e.getFecha(), e.getNombre(), e.getHoras()
        );
    }

    public void insertarDatosStarWars() throws IOException {
        session.execute("CREATE DATABASE starWars");
        session.execute("ADD TO starWars " + dbStarWArs);

    }

    public void insertarNodoSubnivel() throws IOException {

    }

    private static String dbStarWArs = """
            <StarWars>
                <Personaje id="001" estado="vivo">
                    <Nombre>Luke Skywalker</Nombre>
                    <Afiliacion>Orden Jedi, Alianza Rebelde, Nueva República</Afiliacion>
                    <Especie>Humano</Especie>
                    <PlanetaNatal>Tatooine</PlanetaNatal>
                    <Altura unidad="m">1.72</Altura>
                    <Edad>53</Edad>
                    <FechaNacimiento>19BBY</FechaNacimiento>
                    <ColorSable>azul</ColorSable>
                    <TieneSable>true</TieneSable>
                    <NivelPoder>95</NivelPoder>
                    <Habilidades>
                        <Habilidad nivel="avanzado">Telequinesis</Habilidad>
                        <Habilidad nivel="experto">Combate con sable</Habilidad>
                        <Habilidad nivel="intermedio">Persuasión Jedi</Habilidad>
                    </Habilidades>
                    <PeliculasParticipa>
                        <Pelicula año="1977">Una Nueva Esperanza</Pelicula>
                        <Pelicula año="1980">El Imperio Contraataca</Pelicula>
                        <Pelicula año="1983">El Retorno del Jedi</Pelicula>
                        <Pelicula año="2015">El Despertar de la Fuerza</Pelicula>
                        <Pelicula año="2017">Los Últimos Jedi</Pelicula>
                        <Pelicula año="2019">El Ascenso de Skywalker</Pelicula>
                    </PeliculasParticipa>
                </Personaje>
            
                <Personaje id="002" estado="fallecido">
                    <Nombre>Darth Vader</Nombre>
                    <Afiliacion>Imperio Galáctico, Sith</Afiliacion>
                    <Especie>Humano</Especie>
                    <PlanetaNatal>Tatooine</PlanetaNatal>
                    <Altura unidad="m">2.02</Altura>
                    <Edad>45</Edad>
                    <FechaNacimiento>41BBY</FechaNacimiento>
                    <ColorSable>rojo</ColorSable>
                    <TieneSable>true</TieneSable>
                    <NivelPoder>99</NivelPoder>
                    <Habilidades>
                        <Habilidad nivel="experto">Asfixia de la Fuerza</Habilidad>
                        <Habilidad nivel="avanzado">Combate con sable</Habilidad>
                        <Habilidad nivel="intermedio">Persuasión Jedi</Habilidad>
                    </Habilidades>
                    <PeliculasParticipa>
                        <Pelicula año="1977">Una Nueva Esperanza</Pelicula>
                        <Pelicula año="1980">El Imperio Contraataca</Pelicula>
                        <Pelicula año="1983">El Retorno del Jedi</Pelicula>
                        <Pelicula año="2005">La Venganza de los Sith</Pelicula>
                        <Pelicula año="2016">Rogue One</Pelicula>
                    </PeliculasParticipa>
                </Personaje>
            
                <Personaje id="003" estado="desconocido">
                    <Nombre>Yoda</Nombre>
                    <Afiliacion>Orden Jedi</Afiliacion>
                    <Especie>Desconocida</Especie>
                    <PlanetaNatal>Desconocido</PlanetaNatal>
                    <Altura unidad="m">0.66</Altura>
                    <Edad>900</Edad>
                    <FechaNacimiento>896BBY</FechaNacimiento>
                    <ColorSable>verde</ColorSable>
                    <TieneSable>true</TieneSable>
                    <NivelPoder>100</NivelPoder>
                    <Habilidades>
                        <Habilidad nivel="experto">Salto de la Fuerza</Habilidad>
                        <Habilidad nivel="experto">Telequinesis</Habilidad>
                        <Habilidad nivel="experto">Visión del futuro</Habilidad>
                    </Habilidades>
                    <PeliculasParticipa>
                        <Pelicula año="1980">El Imperio Contraataca</Pelicula>
                        <Pelicula año="1983">El Retorno del Jedi</Pelicula>
                        <Pelicula año="1999">La Amenaza Fantasma</Pelicula>
                        <Pelicula año="2002">El Ataque de los Clones</Pelicula>
                        <Pelicula año="2005">La Venganza de los Sith</Pelicula>
                    </PeliculasParticipa>
                </Personaje>
            
                <Personaje id="004" estado="vivo">
                    <Nombre>Leia Organa</Nombre>
                    <Afiliacion>Alianza Rebelde, Nueva República, Orden Jedi</Afiliacion>
                    <Especie>Humana</Especie>
                    <PlanetaNatal>Alderaan</PlanetaNatal>
                    <Altura unidad="m">1.50</Altura>
                    <Edad>54</Edad>
                    <FechaNacimiento>19BBY</FechaNacimiento>
                    <ColorSable>azul</ColorSable>
                    <TieneSable>true</TieneSable>
                    <NivelPoder>70</NivelPoder>
                    <Habilidades>
                        <Habilidad nivel="intermedio">Persuasión Jedi</Habilidad>
                        <Habilidad nivel="avanzado">Estrategia militar</Habilidad>
                    </Habilidades>
                    <PeliculasParticipa>
                        <Pelicula año="1977">Una Nueva Esperanza</Pelicula>
                        <Pelicula año="1980">El Imperio Contraataca</Pelicula>
                        <Pelicula año="1983">El Retorno del Jedi</Pelicula>
                        <Pelicula año="2015">El Despertar de la Fuerza</Pelicula>
                        <Pelicula año="2017">Los Últimos Jedi</Pelicula>
                        <Pelicula año="2019">El Ascenso de Skywalker</Pelicula>
                    </PeliculasParticipa>
                </Personaje>
            
                <Personaje id="005" estado="vivo">
                    <Nombre>Han Solo</Nombre>
                    <Afiliacion>Contrabandista, Alianza Rebelde, Nueva República</Afiliacion>
                    <Especie>Humano</Especie>
                    <PlanetaNatal>Corellia</PlanetaNatal>
                    <Altura unidad="m">1.80</Altura>
                    <Edad>65</Edad>
                    <FechaNacimiento>32BBY</FechaNacimiento>
                    <ColorSable>No usa</ColorSable>
                    <TieneSable>false</TieneSable>
                    <NivelPoder>50</NivelPoder>
                    <Habilidades>
                        <Habilidad nivel="experto">Pilotaje</Habilidad>
                        <Habilidad nivel="avanzado">Disparo</Habilidad>
                        <Habilidad nivel="intermedio">Persuasión</Habilidad>
                    </Habilidades>
                    <PeliculasParticipa>
                        <Pelicula año="1977">Una Nueva Esperanza</Pelicula>
                        <Pelicula año="1980">El Imperio Contraataca</Pelicula>
                        <Pelicula año="1983">El Retorno del Jedi</Pelicula>
                        <Pelicula año="2015">El Despertar de la Fuerza</Pelicula>
                        <Pelicula año="2018">Solo: Una Historia de Star Wars</Pelicula>
                    </PeliculasParticipa>
                </Personaje>
            
                <Personaje id="006" estado="vivo">
                <Nombre>Han Solo</Nombre>
                <Afiliacion>Alianza Rebelde</Afiliacion>
                <Especie>Humano</Especie>
                <PlanetaNatal>Corellia</PlanetaNatal>
                <Altura unidad="m">1.80</Altura>
                <Edad>63</Edad>
                <FechaNacimiento>29BBY</FechaNacimiento>
                <ColorSable>ninguno</ColorSable>
                <TieneSable>false</TieneSable>
                <NivelPoder>50</NivelPoder>
                <Habilidades>
                    <Habilidad nivel="experto">Pilotaje</Habilidad>
                    <Habilidad nivel="avanzado">Combate cuerpo a cuerpo</Habilidad>
                </Habilidades>
                <PeliculasParticipa>
                    <Pelicula año="1977">Una Nueva Esperanza</Pelicula>
                    <Pelicula año="1980">El Imperio Contraataca</Pelicula>
                    <Pelicula año="1983">El Retorno del Jedi</Pelicula>
                    <Pelicula año="2015">El Despertar de la Fuerza</Pelicula>
                </PeliculasParticipa>
            </Personaje>
            
            <Personaje id="007" estado="vivo">
                <Nombre>Chewbacca</Nombre>
                <Afiliacion>Alianza Rebelde</Afiliacion>
                <Especie>Wookiee</Especie>
                <PlanetaNatal>Kashyyyk</PlanetaNatal>
                <Altura unidad="m">2.28</Altura>
                <Edad>200</Edad>
                <FechaNacimiento>200BBY</FechaNacimiento>
                <ColorSable>ninguno</ColorSable>
                <TieneSable>false</TieneSable>
                <NivelPoder>60</NivelPoder>
                <Habilidades>
                    <Habilidad nivel="experto">Fuerza física</Habilidad>
                    <Habilidad nivel="avanzado">Ingeniería</Habilidad>
                </Habilidades>
                <PeliculasParticipa>
                    <Pelicula año="1977">Una Nueva Esperanza</Pelicula>
                    <Pelicula año="1980">El Imperio Contraataca</Pelicula>
                    <Pelicula año="1983">El Retorno del Jedi</Pelicula>
                    <Pelicula año="2015">El Despertar de la Fuerza</Pelicula>
                </PeliculasParticipa>
            </Personaje>
            
            <Personaje id="008" estado="vivo">
                <Nombre>Rey</Nombre>
                <Afiliacion>Orden Jedi</Afiliacion>
                <Especie>Humana</Especie>
                <PlanetaNatal>Jakku</PlanetaNatal>
                <Altura unidad="m">1.70</Altura>
                <Edad>22</Edad>
                <FechaNacimiento>15ABY</FechaNacimiento>
                <ColorSable>azul</ColorSable>
                <TieneSable>true</TieneSable>
                <NivelPoder>85</NivelPoder>
                <Habilidades>
                    <Habilidad nivel="avanzado">Telequinesis</Habilidad>
                    <Habilidad nivel="intermedio">Persuasión Jedi</Habilidad>
                </Habilidades>
                <PeliculasParticipa>
                    <Pelicula año="2015">El Despertar de la Fuerza</Pelicula>
                    <Pelicula año="2017">Los Últimos Jedi</Pelicula>
                    <Pelicula año="2019">El Ascenso de Skywalker</Pelicula>
                </PeliculasParticipa>
            </Personaje>
            
            <Personaje id="009" estado="fallecido">
                <Nombre>Palpatine</Nombre>
                <Afiliacion>Imperio Galáctico, Sith</Afiliacion>
                <Especie>Humano</Especie>
                <PlanetaNatal>Naboo</PlanetaNatal>
                <Altura unidad="m">1.73</Altura>
                <Edad>84</Edad>
                <FechaNacimiento>82BBY</FechaNacimiento>
                <ColorSable>rojo</ColorSable>
                <TieneSable>true</TieneSable>
                <NivelPoder>100</NivelPoder>
                <Habilidades>
                    <Habilidad nivel="experto">Relámpagos de la Fuerza</Habilidad>
                    <Habilidad nivel="experto">Manipulación</Habilidad>
                </Habilidades>
                <PeliculasParticipa>
                    <Pelicula año="1983">El Retorno del Jedi</Pelicula>
                    <Pelicula año="1999">La Amenaza Fantasma</Pelicula>
                    <Pelicula año="2002">El Ataque de los Clones</Pelicula>
                    <Pelicula año="2005">La Venganza de los Sith</Pelicula>
                    <Pelicula año="2019">El Ascenso de Skywalker</Pelicula>
                </PeliculasParticipa>
            </Personaje>
            </StarWars>
            
            """;


}
