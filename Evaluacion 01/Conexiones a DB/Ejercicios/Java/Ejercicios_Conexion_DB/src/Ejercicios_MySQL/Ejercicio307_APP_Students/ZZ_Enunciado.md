# Ejercicio 307

<p>Queremos conectarnos a la base de datos de un instituto llamada <code>school</code>, que está compuesta de una tabla <code>student</code>, que contiene la información de los diferentes estudiantes.
La tabla puede crearse con las siguientes sentencias:</p>
<div class="language-sql codeBlockContainer_Ckt0 theme-code-block" style="--prism-color:#393A34;--prism-background-color:#f6f8fa"><div class="codeBlockContent_biex"><pre tabindex="0" class="prism-code language-sql codeBlock_bY9V thin-scrollbar" style="color:#393A34;background-color:#f6f8fa"><code class="codeBlockLines_e6Vv"><span class="token-line" style="color:#393A34"><span class="token keyword" style="color:#00009f">DROP</span><span class="token plain"> </span><span class="token keyword" style="color:#00009f">DATABASE</span><span class="token plain"> </span><span class="token keyword" style="color:#00009f">IF</span><span class="token plain"> </span><span class="token keyword" style="color:#00009f">EXISTS</span><span class="token plain"> school</span><span class="token punctuation" style="color:#393A34">;</span><span class="token plain"></span><br></span><span class="token-line" style="color:#393A34"><span class="token plain"></span><span class="token keyword" style="color:#00009f">CREATE</span><span class="token plain"> </span><span class="token keyword" style="color:#00009f">DATABASE</span><span class="token plain"> school</span><span class="token punctuation" style="color:#393A34">;</span><span class="token plain"></span><br></span><span class="token-line" style="color:#393A34"><span class="token plain"></span><span class="token keyword" style="color:#00009f">USE</span><span class="token plain"> school</span><span class="token punctuation" style="color:#393A34">;</span><span class="token plain"></span><br></span><span class="token-line" style="color:#393A34"><span class="token plain" style="display:inline-block"></span><br></span><span class="token-line" style="color:#393A34"><span class="token plain"></span><span class="token keyword" style="color:#00009f">CREATE</span><span class="token plain"> </span><span class="token keyword" style="color:#00009f">TABLE</span><span class="token plain"> student </span><span class="token punctuation" style="color:#393A34">(</span><span class="token plain"></span><br></span><span class="token-line" style="color:#393A34"><span class="token plain">    id </span><span class="token keyword" style="color:#00009f">CHAR</span><span class="token punctuation" style="color:#393A34">(</span><span class="token number" style="color:#36acaa">9</span><span class="token punctuation" style="color:#393A34">)</span><span class="token plain"> </span><span class="token keyword" style="color:#00009f">PRIMARY</span><span class="token plain"> </span><span class="token keyword" style="color:#00009f">KEY</span><span class="token punctuation" style="color:#393A34">,</span><span class="token plain"></span><br></span><span class="token-line" style="color:#393A34"><span class="token plain">    name </span><span class="token keyword" style="color:#00009f">VARCHAR</span><span class="token punctuation" style="color:#393A34">(</span><span class="token number" style="color:#36acaa">50</span><span class="token punctuation" style="color:#393A34">)</span><span class="token plain"> </span><span class="token operator" style="color:#393A34">NOT</span><span class="token plain"> </span><span class="token boolean" style="color:#36acaa">NULL</span><span class="token punctuation" style="color:#393A34">,</span><span class="token plain"></span><br></span><span class="token-line" style="color:#393A34"><span class="token plain">    surname </span><span class="token keyword" style="color:#00009f">VARCHAR</span><span class="token punctuation" style="color:#393A34">(</span><span class="token number" style="color:#36acaa">100</span><span class="token punctuation" style="color:#393A34">)</span><span class="token plain"> </span><span class="token operator" style="color:#393A34">NOT</span><span class="token plain"> </span><span class="token boolean" style="color:#36acaa">NULL</span><span class="token punctuation" style="color:#393A34">,</span><span class="token plain"></span><br></span><span class="token-line" style="color:#393A34"><span class="token plain">    age </span><span class="token keyword" style="color:#00009f">INT</span><span class="token plain"> </span><span class="token operator" style="color:#393A34">NOT</span><span class="token plain"> </span><span class="token boolean" style="color:#36acaa">NULL</span><span class="token plain"></span><br></span><span class="token-line" style="color:#393A34"><span class="token plain"></span><span class="token punctuation" style="color:#393A34">)</span><span class="token punctuation" style="color:#393A34">;</span><span class="token plain"></span><br></span><span class="token-line" style="color:#393A34"><span class="token plain" style="display:inline-block"></span><br></span><span class="token-line" style="color:#393A34"><span class="token plain"></span><span class="token keyword" style="color:#00009f">INSERT</span><span class="token plain"> </span><span class="token keyword" style="color:#00009f">INTO</span><span class="token plain"> student </span><span class="token keyword" style="color:#00009f">VALUES</span><span class="token plain"> </span><span class="token punctuation" style="color:#393A34">(</span><span class="token string" style="color:#e3116c">&#x27;11111111A&#x27;</span><span class="token punctuation" style="color:#393A34">,</span><span class="token string" style="color:#e3116c">&#x27;Draco&#x27;</span><span class="token punctuation" style="color:#393A34">,</span><span class="token string" style="color:#e3116c">&#x27;Malfoy&#x27;</span><span class="token punctuation" style="color:#393A34">,</span><span class="token number" style="color:#36acaa">25</span><span class="token punctuation" style="color:#393A34">)</span><span class="token punctuation" style="color:#393A34">;</span><span class="token plain"></span><br></span><span class="token-line" style="color:#393A34"><span class="token plain"></span><span class="token keyword" style="color:#00009f">INSERT</span><span class="token plain"> </span><span class="token keyword" style="color:#00009f">INTO</span><span class="token plain"> student </span><span class="token keyword" style="color:#00009f">VALUES</span><span class="token plain"> </span><span class="token punctuation" style="color:#393A34">(</span><span class="token string" style="color:#e3116c">&#x27;22222222B&#x27;</span><span class="token punctuation" style="color:#393A34">,</span><span class="token string" style="color:#e3116c">&#x27;Hermione&#x27;</span><span class="token punctuation" style="color:#393A34">,</span><span class="token string" style="color:#e3116c">&#x27;Granger&#x27;</span><span class="token punctuation" style="color:#393A34">,</span><span class="token number" style="color:#36acaa">23</span><span class="token punctuation" style="color:#393A34">)</span><span class="token punctuation" style="color:#393A34">;</span><span class="token plain"></span><br></span><span class="token-line" style="color:#393A34"><span class="token plain"></span><span class="token keyword" style="color:#00009f">INSERT</span><span class="token plain"> </span><span class="token keyword" style="color:#00009f">INTO</span><span class="token plain"> student </span><span class="token keyword" style="color:#00009f">VALUES</span><span class="token plain"> </span><span class="token punctuation" style="color:#393A34">(</span><span class="token string" style="color:#e3116c">&#x27;33333333C&#x27;</span><span class="token punctuation" style="color:#393A34">,</span><span class="token string" style="color:#e3116c">&#x27;Harry&#x27;</span><span class="token punctuation" style="color:#393A34">,</span><span class="token string" style="color:#e3116c">&#x27;Potter&#x27;</span><span class="token punctuation" style="color:#393A34">,</span><span class="token number" style="color:#36acaa">20</span><span class="token punctuation" style="color:#393A34">)</span><span class="token punctuation" style="color:#393A34">;</span><span class="token plain"></span><br></span><span class="token-line" style="color:#393A34"><span class="token plain"></span><span class="token keyword" style="color:#00009f">INSERT</span><span class="token plain"> </span><span class="token keyword" style="color:#00009f">INTO</span><span class="token plain"> student </span><span class="token keyword" style="color:#00009f">VALUES</span><span class="token plain"> </span><span class="token punctuation" style="color:#393A34">(</span><span class="token string" style="color:#e3116c">&#x27;44444444D&#x27;</span><span class="token punctuation" style="color:#393A34">,</span><span class="token string" style="color:#e3116c">&#x27;Ron&#x27;</span><span class="token punctuation" style="color:#393A34">,</span><span class="token string" style="color:#e3116c">&#x27;Weasley&#x27;</span><span class="token punctuation" style="color:#393A34">,</span><span class="token number" style="color:#36acaa">22</span><span class="token punctuation" style="color:#393A34">)</span><span class="token punctuation" style="color:#393A34">;</span><br></span></code></pre><div class="buttonGroup__atx"><button type="button" aria-label="Copiar código" title="Copiar" class="clean-btn"><span class="copyButtonIcons_eSgA" aria-hidden="true"><svg viewBox="0 0 24 24" class="copyButtonIcon_y97N"><path fill="currentColor" d="M19,21H8V7H19M19,5H8A2,2 0 0,0 6,7V21A2,2 0 0,0 8,23H19A2,2 0 0,0 21,21V7A2,2 0 0,0 19,5M16,1H4A2,2 0 0,0 2,3V17H4V3H16V1Z"></path></svg><svg viewBox="0 0 24 24" class="copyButtonSuccessIcon_LjdS"><path fill="currentColor" d="M21,7L9,19L3.5,13.5L4.91,12.09L9,16.17L19.59,5.59L21,7Z"></path></svg></span></button></div></div></div>
<p>Debes diseñar una aplicación en Java llamada <code>app-students</code> que se conecte a la base de datos y permita realizar varias operaciones sobre ella. Para ello, debes definir una clase <code>Student</code> que disponga de los siguientes atributos:</p>
<ul>
<li>id: String.: hace referencia al DNI.</li>
<li>name: String.</li>
<li>surname: String.</li>
<li>age: int.</li>
</ul>
<p>También debes añadirle los métodos que consideres necesarios.</p>
<p>Por otra parte, deberás crear una clase llamada <code>ManageStudents</code>, que permitirá realizar diferentes operaciones sobre la base de datos del instituto. La clase tendrá, como mínimo, los siguientes atributos y métodos:</p>
<ul>
<li><code>Connection connection</code>.</li>
<li><code>void openConnection()</code>.</li>
<li><code>void closeConnection()</code>.</li>
<li><code>boolean addStudent(Student student)</code>: añade un nuevo estudiante a la base de datos.</li>
<li><code>Student getStudent(String id)</code>: obtiene la información de un estudiante determinado.</li>
<li><code>boolean deleteStudent(String id)</code>: borra la información de un estudiante determinado.</li>
<li><code>boolean modifyStudent(Student student)</code>: modifica los datos de un estudiante determinado, si ya existe en la base de datos.</li>
<li><code>ArrayList&lt;Student&gt; getStudentsList()</code>: devuelve una lista con todos los estudiantes almacenados en la BD.</li>
</ul>
<p>Después, debes definir una clase <code>AppStudents</code> en la que le muestres al usuario un menú con las siguientes opciones:</p>
<ol>
<li>MATRICULAR UN ESTUDIANTE.</li>
<li>DAR DE BAJA A UN ESTUDIANTE.</li>
<li>ACTUALIZAR DATOS DE UN ESTUDIANTE.</li>
<li>VER DATOS DE UN ESTUDIANTE.</li>
<li>VER DATOS DE TODOS LOS ESTUDIANTES.</li>
<li>SALIR.</li>
</ol>
<p>En el caso de que no sea posible realizar alguna de las operaciones, debemos mostrarle un mensaje descriptivo al usuario y continuar con la ejecución de la aplicación.</p>