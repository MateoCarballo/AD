# Tarea 401 - Plataforma de Comercio Electrónico para Videojuegos

## Enunciado de la Entrega

El fichero a entregar será `401.zip` y contendrá los siguientes archivos:

- `mongodb.js`: Archivo con el código necesario para generar la base de datos en MongoDB.
- `basex.xml`: Archivo XML para generar la base de datos en BaseX.
- `src/`: Carpeta con el código fuente de la aplicación en Java.

### Consideraciones Generales

- Se debe implementar un menú claro y conciso para realizar las operaciones requeridas.
- Todos los mensajes mostrados (información, entrada de datos y errores) deben ser comprensibles.
- Se penalizará el uso de Java para obtener resultados si existe una consulta directa en XQuery o MongoDB.
- Cualquier código que:
    - Se detecte como copia.
    - No compile ni ejecute correctamente.

  No será corregido y recibirá una puntuación de 0.

## Descripción del Proyecto

El objetivo es desarrollar una plataforma de comercio electrónico para la compra de videojuegos. Los usuarios podrán explorar videojuegos, añadirlos a su carrito, realizar compras y gestionar sus cuentas. La aplicación usará dos bases de datos:

1. **Base de Datos en BaseX**: Contiene datos estáticos de videojuegos.
2. **Base de Datos en MongoDB**: Gestiona información dinámica como usuarios, compras y carritos.

### Estructura de la Base de Datos en MongoDB

Se ha optado por usar tres colecciones separadas para organizar los datos:

- `Usuario`: Almacena la información personal de cada usuario.
- `Carrito`: Contiene la lista de videojuegos que el usuario ha seleccionado para comprar.
- `Compras`: Registra los videojuegos que el usuario ha comprado.

## Funcionalidades a Implementar

El menú permitirá realizar las siguientes operaciones sobre BaseX y MongoDB.

### Operaciones en BaseX (XML)

1. Modificar el valor de un elemento según un ID.
2. Eliminar un videojuego según su ID.
3. Listar todos los videojuegos ordenados por plataforma y título.
4. Listar videojuegos filtrados por edad mínima recomendada.
5. Mostrar el videojuego más barato por plataforma.
6. Buscar videojuegos por una subcadena en su descripción.
7. Calcular la cantidad total de videojuegos por plataforma y su porcentaje.
8. Calcular el precio total de todos los videojuegos disponibles.

### Operaciones en MongoDB

9. Crear un nuevo usuario (sin emails repetidos).
10. Identificar usuario por email.
11. Borrar un usuario.
12. Modificar la información de un usuario.
13. Añadir videojuegos al carrito.
14. Mostrar el contenido del carrito y su coste total.
15. Comprar el carrito (confirmando la compra y registrándola en `Compras`).
16. Mostrar las compras de un usuario con sus fechas.
17. Calcular el coste total de cada carrito y listar los resultados de mayor a menor.
18. Calcular el total gastado por cada usuario en compras y ordenarlos de menor a mayor.

## Configuración y Ejecución

Para ejecutar la aplicación, es necesario:

1. **Crear las bases de datos**:
    - BaseX: `videojuegos`
    - MongoDB: `comercio`

2. **Clases de Conexión**:
    - `conexion.ConexionBaseX.java`: Maneja la conexión con BaseX.
    - `conexion.ConexionMongo.java`: Maneja la conexión con MongoDB.

3. **Clase `StringResource`**:
    - Contiene menús y código repetitivo para mantener limpio el código principal.

## Notas Adicionales

- Se recomienda revisar las estructuras de documentos en MongoDB para enlazar correctamente las colecciones.
- El script `mongodb.js` debe contener datos suficientes para que las consultas 17 y 18 funcionen sin ejecutar otras operaciones previas.

---