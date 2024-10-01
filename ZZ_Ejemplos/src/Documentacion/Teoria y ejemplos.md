**MANEJO DE ARCHIVOS DE TEXTO**
```          
// 1. Crear un archivo de texto con FileWriter
FileWriter fw = new FileWriter("archivo.txt");
fw.write("Esto es un archivo de texto.\n");
fw.write("Otra línea de texto.\n");
fw.close();

// 2. Leer el archivo de texto con FileReader
FileReader fr = new FileReader("archivo.txt");
int caracterLeido;
System.out.print("Contenido del archivo de texto:\n");
while ((caracterLeido = fr.read()) != -1) {
    System.out.print((char) caracterLeido);
}
fr.close();
```

**MANEJO DE ARCHIVOS BINARIOS**
```
// 3. Escribir datos binarios con DataOutputStream
DataOutputStream dos = new DataOutputStream(new FileOutputStream("archivo.bin"));
dos.writeInt(123);
dos.writeFloat(45.67f);
dos.writeBoolean(true);
dos.close();

// 4. Leer datos binarios con DataInputStream
DataInputStream dis = new DataInputStream(new FileInputStream("archivo.bin"));
int numero = dis.readInt();
float decimal = dis.readFloat();
boolean valor = dis.readBoolean();
System.out.println("\nDatos binarios leídos:");
System.out.println("Entero: " + numero);
System.out.println("Flotante: " + decimal);
System.out.println("Booleano: " + valor);
dis.close();
```

**Clases relacionadas con la estructura del archivo:**

* File: Representa un archivo o directorio. No maneja contenido, solo el archivo en sí.

* Flujos de bytes (archivos binarios):

    * FileInputStream: Leer bytes desde un archivo.
    * FileOutputStream: Escribir bytes a un archivo.
* Flujos de caracteres (archivos de texto):

    * FileReader: Leer caracteres desde un archivo.
    * FileWriter: Escribir caracteres a un archivo.
* Flujos de datos (lectura y escritura de tipos primitivos):

    * DataInputStream: Leer tipos primitivos (int, float, boolean, etc.) desde un archivo.
    * DataOutputStream: Escribir tipos primitivos a un archivo.
