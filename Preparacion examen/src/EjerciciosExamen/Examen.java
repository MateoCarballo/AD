package EjerciciosExamen;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class Examen {
    final static String RUTA_CARPETA = "src" + File.separator + "EjerciciosExamen" + File.separator + "resources";
    final static String NOMBRE_ARCHIVO_DAT = "ejercicio2.dat";
    public static void main(String[] args) throws IOException, TransformerException, ParserConfigurationException, SAXException {
        ejercicio2();
        ejercicio3();
        //ejercicio4();
        ejercicio5();
    }



    private static void ejercicio5() throws SAXException, IOException, ParserConfigurationException {
        File ficheroXML = new File("src/EjerciciosExamen/resources/ejercicio4/usuarios.xml");
        try {
           Document archivoxml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(ficheroXML);
           archivoxml.getDocumentElement().normalize();
           Element usuarios = archivoxml.getDocumentElement();
           System.out.println("<" + archivoxml.getDocumentElement().getNodeName() + " "+ usuarios.getAttributeNode("region").getName() + "=" +archivoxml.getDocumentElement().getAttribute("region")+">");

           NodeList listaDeNodos = archivoxml.getElementsByTagName("usuario");

            for (int i = 0; i < listaDeNodos.getLength(); i++) {
                Node nodoUsuario = listaDeNodos.item(i);
                System.out.println("    <" + nodoUsuario.getNodeName()+">");
                if (nodoUsuario.getNodeType() == Node.ELEMENT_NODE){
                    Element elementoUsuario = (Element) nodoUsuario;
                    System.out.println("        <" + "nombre" +">"+ elementoUsuario.getElementsByTagName("nombre").item(0).getTextContent() + "</nombre>" );
                    System.out.println("        <" + "clave" +">"+ elementoUsuario.getElementsByTagName("clave").item(0).getTextContent() +"</nombre>");
                }
            }


        } catch (ParserConfigurationException e) {
            throw new ParserConfigurationException("Error");
        } catch (IOException e) {
            throw new IOException(e);
        } catch (SAXException e) {
            throw new SAXException(e);
        }
    }

    private static void ejercicio4() throws ParserConfigurationException, TransformerException {
        ejercicio4CrearDirectorio();
        ejercicio4CrearDocument();
    }

    private static void ejercicio4CrearDocument() throws ParserConfigurationException, TransformerException {
        try {
            //Creacion de la representacion en nuestro programa del xml(el archivo xml no existe todavia)
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().getDOMImplementation().createDocument(null,"usuarios",null);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");

            //Rellenar de contenido el xml
            Element usuarios = document.getDocumentElement();
            Attr region=document.createAttribute("region");
            region.setValue("Europa");
            usuarios.setAttributeNode(region);

            Element usuario=document.createElement("usuario");
            Attr premium=document.createAttribute("premium");
            premium.setValue("no");
            Element nombre= document.createElement("nombre");
            nombre.setTextContent("Mateo");
            Element clave= document.createElement("clave");
            clave.setTextContent("1234");

            usuario.appendChild(nombre);
            usuario.appendChild(clave);
            usuario.setAttributeNode(premium);
            usuarios.appendChild(usuario);

            usuario=document.createElement("usuario");
            premium=document.createAttribute("premium");
            premium.setValue("si");
            nombre= document.createElement("nombre");
            nombre.setTextContent("Adrian");
            clave= document.createElement("clave");
            clave.setTextContent("6789");

            usuario.appendChild(nombre);
            usuario.appendChild(clave);
            usuario.setAttributeNode(premium);
            usuarios.appendChild(usuario);

            usuario=document.createElement("usuario");
            premium=document.createAttribute("premium");
            premium.setValue("no");
            nombre= document.createElement("nombre");
            nombre.setTextContent("David");
            clave= document.createElement("clave");
            clave.setTextContent("5678");

            usuario.appendChild(nombre);
            usuario.appendChild(clave);
            usuario.setAttributeNode(premium);
            usuarios.appendChild(usuario);

            DOMSource domSource=new DOMSource(document);
            StreamResult streamResult=new StreamResult(new File("src/EjerciciosExamen/resources/ejercicio4/usuarios.xml"));
            transformer.transform(domSource,streamResult);
        } catch (ParserConfigurationException e) {
            throw new ParserConfigurationException("error");
        } catch (TransformerConfigurationException e) {
            throw new TransformerConfigurationException("Error");
        } catch (TransformerException e) {
            throw new TransformerException("Transformer Exception");
        }
    }

    private static void ejercicio4CrearDirectorio() {
        File directorio = new File("src/EjerciciosExamen/resources/ejercicio4");
        if (!directorio.exists()){
            if (directorio.mkdirs()){
                System.out.println("Creado el directorio con ruta -> " + directorio.getPath());
            }
        }
    }

    private static void ejercicio3() {

        /*ejercicio 3
                .Solicita datos consola.
                .Lista los elementos del directorio.
                .Creo una clase que implemente FilenameFilter.
                .Utiliza FilenameFilter para filtrar.
                .Lista los elementos filtrados.*/
        ejercicio3ListarElementosDirectorio(RUTA_CARPETA + File.separator + "ejercicio3");
        ejercicio3FiltrarPorExtension(RUTA_CARPETA + File.separator + "ejercicio3");
    }

    private static void ejercicio3FiltrarPorExtension(String path) {
        File rutaCarpeta = new File(path);
        File [] listFiles = rutaCarpeta.listFiles();
        FiltradoExtensiones fe = new FiltradoExtensiones(".dat");
        for (File f : listFiles){
            if (fe.accept(f,"etiquetas")){
                System.out.println("********************" + f.getName());
            }
        }
    }

    private static void ejercicio3ListarElementosDirectorio(String rutaParaListar) {
        File path = new File(rutaParaListar);

        if (!path.exists()){
            path.mkdirs();
        }

        File archivo1 = new File (rutaParaListar + File.separator + "archivo de texto.txt");
        if (!archivo1.exists()){
            try {
                archivo1.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        File archivo2 = new File (rutaParaListar + File.separator + "archivo binario.bin");
        if (!archivo1.exists()){
            try {
                archivo2.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        File archivo3 = new File (rutaParaListar + File.separator + "archivo de objetos.dat");
        if (!archivo3.exists()){
            try {
                archivo3.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        File archivo4 = new File (rutaParaListar + File.separator + "archivo con etiquetas.xml");
        if (!archivo4.exists()){
            try {
                archivo4.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        File [] listFiles = path.listFiles();
        for (File f : listFiles){
            System.out.println(f.getName());
        }
    }

    private static void ejercicio2() throws IOException {
           /*ejercicio 2_
         .creación fichero.
        clase Usuario para almacenarlo.
        .Almacena objeto.
        .Utiliza clases binarias.
        .Escritura fichero.
        .Lectura fichero.*/


        ejercicio2CrearDirectorio(RUTA_CARPETA + File.separator + "ejercicio2");
        ejercicio2CrearArchivo(RUTA_CARPETA + File.separator + "ejercicio2" + File.separator + "ejercicio2.dat");
        ejercicio2AlmacenarObjeto(new Usuario("nombre","contrasena","usuario"));
        ejercicio2EscrituraArchivo("Fichero escrito en binario.txt");
        ejercicio2LecturaFichero(RUTA_CARPETA + File.separator + "ejercicio2" + File.separator + "Fichero escrito en binario.txt");

    }

    private static void ejercicio2LecturaFichero(String rutaCompletaAlArchivo) throws IOException {
        try (FileInputStream fis = new FileInputStream(new File(rutaCompletaAlArchivo))){
            StringBuilder reconstruccion = new StringBuilder("");
            int enteroLeido = fis.read();
            while (enteroLeido != -1) {
                reconstruccion.append((char) enteroLeido);
                enteroLeido = fis.read();
            }
            System.out.println(reconstruccion);

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Lanzada excepcion FileNotFoundException desde el metodo \" ejercicio2LecturaFichero \" ");
        } catch (IOException e) {
            throw new IOException("Lanzada excepcion IOException desde el metodo \" ejercicio2LecturaFichero \" ");
        }
    }

    private static void ejercicio2EscrituraArchivo(String nombreArchivo) {
        File f = new File(RUTA_CARPETA + File.separator + "ejercicio2" + File.separator + nombreArchivo );
        try (FileOutputStream fos = new FileOutputStream(f)){
            if (!f.exists()){
                if (f.createNewFile()){
                    System.out.println("Creado el archivo -> " + f.getPath());
                }
            }
            String contenido = "Este es el contenido que quiero escribir.";
            char [] arrayDeChars = contenido.toCharArray();
            for (char c : arrayDeChars) {
                fos.write(c);
            }
            fos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    private static void ejercicio2AlmacenarObjeto(Usuario user) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(RUTA_CARPETA + File.separator + NOMBRE_ARCHIVO_DAT)))) {
            oos.writeObject(user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void ejercicio2CrearArchivo(String archivo) throws IOException {
        File f = new File(archivo);
        try {
            if(f.createNewFile()){
                System.out.println("Creado archivo en la ubicacion -> " + f.getPath());
            }
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    private static void ejercicio2CrearDirectorio(String directorio){
        File f = new File(directorio);
        if (!f.exists()){
            if (f.mkdirs()){
                System.out.println("creado directorio -> " +  f.getPath());
            }
        }
    }
}
