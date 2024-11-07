<p>A partir de la base de datos <a href="/assets/files/createLibrary-15af4551186fdf0979d0991df6948f76.sql" target="_blank">Librería</a> la cual tiene el siguiente diagrama:</p>
<p><img decoding="async" loading="lazy" src="Esquema.png" width="1112" height="1276" class="img_ev3q"></p>
<p>Se pide crear un programa Java que tenga un paquete llamado <code>dataBase</code> que tenga dos clases:</p>
<ul>
<li>
<p>Clase <code>Operaciones.java</code>: que se encargará de la conexión a la base de datos y contendrá las siguientes funciones:</p>
<ul>
<li><code>existsClient</code>: recibirá un id de cliente (<code>idClient</code>) y devolverá <code>true</code> o <code>false</code> indicando si el cliente existe en la BD o no.</li>
<li><code>existsBook</code>: recibe un id de libro (<code>idBook</code>) y devuelve <code>true</code> o <code>false</code> indicando si el libro existe en la BD o no.</li>
<li><code>isBorrowed</code>: recibe el código (<code>code</code>) de un libro y devuelve <code>true</code> o <code>false</code> si el libro está prestado o no.</li>
<li><code>addLoan</code>: recibe el código (<code>code</code>) de un libro y el id de cliente  (<code>idClient</code>) y usa los métodos anteriores para hacer las comprobaciones necesarias y añadir un préstamos a la BD.</li>
<li><code>addReturn</code>: recibe el código de un libro y modifica el campo prestado (<code>borrowed</code>) en la tabla de préstamos (<code>loan</code>).</li>
<li><code>borrowedBooksList</code>: devuelve un ArrayList de libros con la lista de libros prestados hasta el momento.</li>
<li><code>availableBooksList</code>: devuelve un ArrayList de libros con la lista de libros que están disponibles para ser prestados.</li>
</ul>
</li>
<li>
<p>Clase <code>Main.java</code>: permitirá lanzar la aplicación, mostrará un menú que permita:</p>
<ol>
<li>Prestar un libro.</li>
<li>Devolver un libro.</li>
<li>Listar los libros prestados.</li>
<li>Listar los libros que se pueden prestar.</li>
</ol>
</li>
</ul>
<p><strong>NOTA</strong>: tendrán que notificarse todos los posibles errores.</p>