package LecturaEscrituraXML;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ArchivosXML {
    final static String RUTA = "src"+File.separator+"LecturaEscrituraXML"+File.separator+"Archivos";


    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        
        crearXML(File.separator + "Archivo escrito desde programa.xml");
        crearXMLPersonas("Archivo para leer.xml");
//        try {
//            leerXML();
//        } catch (ParserConfigurationException | SAXException | IOException e) {
//            System.out.println("Se ha lanzado una excepcion del tiopo -> " + e.getMessage());
//        }

    }

    private static void crearXMLPersonas(String nombreArchivo) throws TransformerException, ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbf.newDocumentBuilder();
            Document xmlPersonas = dBuilder.getDOMImplementation().createDocument(null,"personas",null);

            Element elementoRaiz = xmlPersonas.getDocumentElement();

            Element nombre =  xmlPersonas.createElement("nombre");
            nombre.setTextContent("nombre de la primera persona");

            Element edad = xmlPersonas.createElement("edad");
            edad.setTextContent("edad persona 1");

            Element cumple = xmlPersonas.createElement("cumpleaños");
            cumple.setTextContent("01/01/2020");

            Element persona = xmlPersonas.createElement("persona");

            Attr nif = xmlPersonas.createAttribute("nif");
            nif.setValue("12345678A");

            persona.setAttributeNode(nif);
            persona.appendChild(nombre);
            persona.appendChild(edad);
            persona.appendChild(cumple);

            elementoRaiz.appendChild(persona);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trnas = tf.newTransformer();

            trnas.setOutputProperty(OutputKeys.INDENT,"yes");

            File archivo = new File (RUTA + File.separator + nombreArchivo);
            DOMSource dom = new DOMSource(xmlPersonas);
            StreamResult sResult = new StreamResult(archivo);
            trnas.transform(dom,sResult);



        } catch (ParserConfigurationException e) {
            throw new ParserConfigurationException("Excepcion ParserConfigurationLanzada");
        } catch (TransformerConfigurationException e) {
            throw new TransformerConfigurationException("Excepcion TransformerConfigurationException");
        } catch (TransformerException e) {
            throw new TransformerException("Excepcion TransformerException");
        }

    }

    private static void leerXML() throws ParserConfigurationException, SAXException, IOException {
        File xml = new File(RUTA + File.separator + "Nombre Documento 1.xml");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document documentoxml = db.parse(xml);

            documentoxml.getDocumentElement().normalize();
            System.out.println("<" + documentoxml.getDocumentElement().getNodeName() + ">");

            NodeList nList = documentoxml.getElementsByTagName("elementoNivel1");
            for (int i = 0; i < nList.getLength(); i++) {
                Node n = nList.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE){
                    Element e = (Element) n;
                    System.out.println("    <" + n.getNodeName() + ">");
                }
            }


        } catch (ParserConfigurationException e) {
            throw new ParserConfigurationException("Error ParserConfigurationException");
        } catch (IOException e) {
            throw new IOException("Error IOException");
        } catch (SAXException e) {
            throw new SAXException("Error SAXException");
        }

    }

    private static void crearXML(String nombreArchivo) {

        /*
        Estructura xml

        -> root
        ---->ElementoNivel1
        ------->Elementonivel2
        ----------->Elementonivel3

         */

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
//            DOMImplementation implementation = docBuilder.getDOMImplementation();
            Document documentoXML = docBuilder.getDOMImplementation().createDocument(null,"ElementoRaiz",null);

            Element root = documentoXML.getDocumentElement();

            Element elementonivel3 = documentoXML.createElement("elementoNivel3");
            Attr atributoNivel3 = documentoXML.createAttribute("atributoValor3");
            atributoNivel3.setValue("3");
            elementonivel3.setAttributeNode(atributoNivel3);


            Element elementoNivel2 = documentoXML.createElement("elementoNivel2");
            elementoNivel2.setTextContent("Contenido del elemento de nivel 2");
            elementoNivel2.appendChild(elementonivel3);

            Element elementoNivel1 = documentoXML.createElement("elementoNivel1");
            elementoNivel1.setTextContent("Contenido del elemento de nivel 1");
            Attr atributoElemento1 = documentoXML.createAttribute("atributoElemento1");
            atributoElemento1.setValue("1");
            elementoNivel1.setAttributeNode(atributoElemento1);
            elementoNivel1.setAttribute("atributoCreadoDirectamente","directamente");

            elementoNivel1.appendChild(elementoNivel2);

            root.appendChild(elementoNivel1);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            // Configuraciones de formateo para una mejor legibilidad
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(documentoXML);

            /*
            Creamos las carpetas para almacenar los archivos xml si no existen, creo un nuevo
            directorio para que se organice mejor la informacion sin nignun propósito específico
             */
            crearRutaSiNoExiste(RUTA);
            crearRutaSiNoExiste("src" + File.separator + "LecturaEscrituraXML" + File.separator + "Otros Archivos");

            StreamResult sr = new StreamResult(new File(RUTA + nombreArchivo));
            transformer.transform(source,sr);
        } catch (ParserConfigurationException e) {
            System.out.println("Lanzada excepcion cuando instanciamos un nuevo document builder");
        } catch (TransformerConfigurationException e) {
            System.out.println("Error durante la creacion del transformer del tipo "+ Arrays.toString(e.getStackTrace()));
        } catch (TransformerException e) {
            System.out.println("Error durante la creacion del fichero del tipo ");
            e.printStackTrace();
        }
    }

    private static void crearRutaSiNoExiste(String ruta) {
        File path = new File(ruta);
        if(!path.exists()){
            if (path.mkdirs()){
                System.out.println("Se ha creado con exito la ruta del fichero -> " + ruta);
            }
        }
    }
}
