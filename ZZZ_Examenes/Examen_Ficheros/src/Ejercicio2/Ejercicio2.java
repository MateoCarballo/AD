package Ejercicio2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

public class Ejercicio2 {
    final static String RUTA_ARCHIVO_XML = "src/Ejercicio2/Recursos/productos.xml";
    final static String RUTA_ARCHIVO_BIN = "src/Ejercicio2/Recursos/producto.bin";

    public static void main(String[] args) throws IOException, ParserConfigurationException {
        crearFicheroSiNoExiste();
        comprobarStockEnElXML("25");
    }

    private static void crearFicheroSiNoExiste() throws IOException {
        File fichero = new File(RUTA_ARCHIVO_BIN);

        if (!fichero.exists()){
            try {
                if (fichero.createNewFile()){
                    System.out.println("Fichero -> " + fichero.getName() + " creado con exito en la ruta " + fichero.getPath());
                }
            } catch (IOException e) {
                throw new IOException("Error durante la creacion del fichero");
            }
        }

        /*
        else{
            leerContenidoArchivoBinario();
        }
         */
    }


    private static void leerContenidoArchivoBinario() {
        File f = new File(RUTA_ARCHIVO_BIN);
        try (DataInputStream dis = new DataInputStream(new FileInputStream(f))) {
            dis.readUTF();
            dis.readFloat();
            dis.readInt();
            //TODO pendiente de comprobar cuantas veces tengo que leer
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void comprobarStockEnElXML(String stock) throws IOException, ParserConfigurationException {
        File ficheroXML = new File (RUTA_ARCHIVO_XML);
        try {
            Document documentoXML = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(ficheroXML);
            documentoXML.normalize();

            NodeList nodeList = documentoXML.getElementsByTagName("producto");
            Element producto;
            Producto p;
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE){
                    producto = (Element) nodeList.item(i);
                    if (producto.getElementsByTagName("stock").item(0).getTextContent().equalsIgnoreCase(stock)){
                        p = new Producto(producto.getElementsByTagName("nombre").item(0).getTextContent(),
                                Float.parseFloat(producto.getElementsByTagName("precio").item(0).getTextContent()),
                                Integer.parseInt(producto.getElementsByTagName("stock").item(0).getTextContent()));
                        escribirProductoEnFichero(p);
                    }

                }
            }

            //TODO
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new IOException(e);
        } catch (ParserConfigurationException e) {
            throw new ParserConfigurationException("Error de parseconfig");
        }
    }

    private static void escribirProductoEnFichero(Producto p) {

        System.out.println("Valor del nombre -> " + p.nombre);
        File ficheroBinario = new File (RUTA_ARCHIVO_BIN);
        /*
        (DataOutputStream dos = new DataOutputStream(new FileOutputStream(ficheroBinario,true))){
            dos.writeUTF(p.nombre);
            dos.writeFloat(p.precio);
            dos.writeInt(p.stock);
            dos.writeUTF("Es para probar");

            dos.flush();
            //TODO
         */
        try(FileWriter fw = new FileWriter(ficheroBinario)){
            char[] stringEnChars = p.nombre.toCharArray();
            for (char c : stringEnChars) {
                fw.write(c);
                fw.write(100);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
