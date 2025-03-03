
# Ejercicio 402

## Descripción

Una vez vista cómo funcionan las bases de datos nativas XML, puede surgir la duda de qué tipo de datos es el ideal para almacenar la información.

Podemos distinguir dos casos:

1. **Almacenar un único fichero muy grande que contiene todos los elementos principales.**
2. **Almacenar muchos ficheros que contengan un único elemento a almacenar.**

### Ejemplo

Si queremos almacenar elementos de tipo *libro*, los cuales tienen la siguiente estructura:

```xml
<libro publicacion="2008" edicion="1">
    <titulo>Titulo1</titulo>
    <autor>
        <nombre>Nombre1</nombre>
        <apellido>Apellido1 Apellido2</apellido>
    </autor>
    <editorial>Editorial1</editorial>
    <paginas>650</paginas>
    <edicionElectronica>true</edicionElectronica>
</libro>
```

Si queremos utilizar el **primer formato** (un único fichero), tendremos un archivo con el siguiente formato:

```xml
<biblioteca>
    <libro>
        ...
    </libro>
    <libro>
        ...
    </libro>
    <libro>
        ...
    </libro>
    <libro>
        ...
    </libro>
</biblioteca>
```

Si, por el contrario, queremos utilizar el **segundo formato** (ficheros pequeños), tendremos muchos archivos con la siguiente estructura:

```xml
<libro>
  ...
</libro>
```

### Objetivo

Basándose en el ejemplo anterior, se pide simular las pruebas necesarias para validar cuál de los dos formatos es más eficiente a la hora de realizar diferentes consultas.

### Requisitos

1. Crear **dos bases de datos**: `ejercicio402FicheroGrande` y `ejercicio402FicherosPequeños`.
2. Crear **10,000 elementos libro** con la estructura del ejemplo anterior.
3. Los datos de cada uno de los elementos anteriores se generarán de forma aleatoria, siguiendo las siguientes directrices:
    - **Cadenas de texto** formadas de la siguiente forma: `Nombre18`, `Editorial12`, etc.
    - **Publicación**: entre 2000 y 2014.
    - **Edición**: entre 1 y 20.
    - **Título**: entre 1 y 20000 caracteres.
    - **Nombre**: entre 0 y 19.
    - **Apellidos**: formados por dos apellidos con valores entre 0 y 19, ejemplo: `Apellido2 Apellido17`.
    - **Editorial**: entre 0 y 100.
    - **Páginas**: entre 150 y 850.
    - **Edición Electrónica**: verdadero o falso.
4. Para evitar distorsionar los resultados, el contenido de todos los elementos de `ficheroGrande` y `ficherosPequeños` debe ser el mismo.

### Consultas a ejecutar

Una vez creadas las dos bases de datos, se deberán ejecutar las siguientes consultas:

1. **Número total de documentos.**
2. **Número de libros publicados antes de 2009.**
3. **Número de libros que escribió un determinado autor.**
4. **Número medio de palabras de todos los libros.**
5. **Número medio de palabras de los libros publicados después de 2008 y escritos por un determinado autor.**

### Tabla de resultados

Al final, se deberá completar la siguiente tabla con el tiempo que tarda cada una de las consultas, de forma que nos permita inferir cuál de las dos opciones es mejor:

| Nº consulta | Fichero Grande | Ficheros Pequeños |
|-------------|----------------|-------------------|
| Consulta 1  |                |                   |
| Consulta 2  |                |                   |
| Consulta 3  |                |                   |
| Consulta 4  |                |                   |
| Consulta 5  |                |                   |
| **Media**   |                |                   |
