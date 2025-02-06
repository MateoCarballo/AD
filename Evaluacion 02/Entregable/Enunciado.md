<h2 class="anchor anchorWithStickyNavbar_LWe7" id="descripción-del-proyecto">Descripción del proyecto<a href="#descripción-del-proyecto" class="hash-link" aria-label="Enlace directo al Descripción del proyecto" title="Enlace directo al Descripción del proyecto">​</a></h2>
<p>En este proyecto vamos a desarrollar una aplicación que permita validar y gestionar la estructura de un hospital.</p>
<p>El diagrama conceptual de la base de datos es el siguiente:</p>
<div class="centrar"><p><img decoding="async" loading="lazy" src="/assets/images/proyecto-2a6ab6808c9b82eab8b35ef2e981b9c8.png" width="879" height="394" class="img_ev3q"></p></div>
<div class="theme-admonition theme-admonition-tip admonition_xJq3 alert alert--success"><div class="admonitionHeading_Gvgb"><span class="admonitionIcon_Rf37"><svg viewBox="0 0 12 16"><path fill-rule="evenodd" d="M6.5 0C3.48 0 1 2.19 1 5c0 .92.55 2.25 1 3 1.34 2.25 1.78 2.78 2 4v1h5v-1c.22-1.22.66-1.75 2-4 .45-.75 1-2.08 1-3 0-2.81-2.48-5-5.5-5zm3.64 7.48c-.25.44-.47.8-.67 1.11-.86 1.41-1.25 2.06-1.45 3.23-.02.05-.02.11-.02.17H5c0-.06 0-.13-.02-.17-.2-1.17-.59-1.83-1.45-3.23-.2-.31-.42-.67-.67-1.11C2.44 6.78 2 5.65 2 5c0-2.2 2.02-4 4.5-4 1.22 0 2.36.42 3.22 1.19C10.55 2.94 11 3.94 11 5c0 .66-.44 1.78-.86 2.48zM4 14h5c-.23 1.14-1.3 2-2.5 2s-2.27-.86-2.5-2z"></path></svg></span>Script de la BD</div><div class="admonitionContent_BuS1"><p>El script correspondiente de la base de datos será <a href="/assets/files/bd-hospital-90415d987fd9ae97f98a6a7eb59e9101.sql" target="_blank">bd-hospital.sql</a> .</p></div></div>
<h2 class="anchor anchorWithStickyNavbar_LWe7" id="tareas-a-realizar">Tareas a realizar<a href="#tareas-a-realizar" class="hash-link" aria-label="Enlace directo al Tareas a realizar" title="Enlace directo al Tareas a realizar">​</a></h2>
<p>Se debe desarrollar una aplicación utilizando Hibernate que tenga las siguientes funcionalidades:</p>
<ul>
<li>Crear, borrar (por id) y modificar los datos de un doctor.</li>
<li>Crear, borrar (por nombre) y modificar los datos de un paciente.</li>
<li>Asignar un doctor a un paciente.<!-- -->
<ul>
<li>La asignación se hará a partir del nombre del doctor y del paciente.</li>
<li>Se pedirá por teclado introducir el nombre del doctor y del paciente.</li>
</ul>
</li>
<li>Indicar la fecha de fin del tratamiento de un paciente.<!-- -->
<ul>
<li>El método recibirá el nombre del paciente, la fecha de inicio, el tipo y la fecha de fin del tratamiento.</li>
</ul>
</li>
<li>Cambiar el hospital de un tratamiento.<!-- -->
<ul>
<li>El método recibirá el id del tratamiento, el nombre del hospital en donde está ahora el tratamiento y el nombre del hospital en dónde se va a realizar el tratamiento a partir de ahora.</li>
</ul>
</li>
<li>Mostrar los datos de un Paciente (id, nombre, fecha_nacimiento, direccion, tratamientos que recibe y citas que tiene).<!-- -->
<ul>
<li>La consulta se hará a partir del nombre del Paciente que introduzca el usuario.</li>
</ul>
</li>
<li>Mostrar los datos de los tratamientos y el hospital en el que se realiza.<!-- -->
<ul>
<li>La consulta se hará a partir del nombre del hospital que introduzca el usuario.</li>
</ul>
</li>
<li>Mostrar el número total de tratamientos que tiene cada hospital.<!-- -->
<ul>
<li>La consulta se hará a partir del nombre del hospital que introduzca el usuario.</li>
</ul>
</li>
</ul>
<div class="theme-admonition theme-admonition-warning admonition_xJq3 alert alert--warning"><div class="admonitionHeading_Gvgb"><span class="admonitionIcon_Rf37"><svg viewBox="0 0 16 16"><path fill-rule="evenodd" d="M8.893 1.5c-.183-.31-.52-.5-.887-.5s-.703.19-.886.5L.138 13.499a.98.98 0 0 0 0 1.001c.193.31.53.501.886.501h13.964c.367 0 .704-.19.877-.5a1.03 1.03 0 0 0 .01-1.002L8.893 1.5zm.133 11.497H6.987v-2.003h2.039v2.003zm0-3.004H6.987V5.987h2.039v4.006z"></path></svg></span>NOTA</div><div class="admonitionContent_BuS1"><ul>
<li>Todas las relaciones tendrán que ser bidireccionales</li>
<li>Todos los datos se pedirán por teclado</li>
<li>Habrá que crear un menú que permita probar cada una de las funcionalidades anteriores</li>
</ul></div></div></div></article><nav class="pagination-nav docusaurus-mt-lg" aria-label="Página del documento"><a class="pagination-nav__link pagination-nav__link--prev" href="/docs/unidades/03/actividades/puntuacion"><div class="pagination-nav__sublabel">Anterior</div><div class="pagination-nav__label">Puntuación</div></a></nav></div></div><div class="col col--3"><div class="tableOfContents_bqdL thin-scrollbar theme-doc-toc-desktop"><ul class="table-of-contents table-of-contents__left-border"><li><a href="#descripción-del-proyecto" class="table-of-contents__link toc-highlight">Descripción del proyecto</a></li><li><a href="#tareas-a-realizar" class="table-of-contents__link toc-highlight">Tareas a realizar</a></li></ul></div></div></div></div></main></div></div></div></div>