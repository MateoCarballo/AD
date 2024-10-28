-- Seleccionar los artículos de color negro y mostrar su número, nombre y peso, así como el nombre del proveedor.
select 
ar.art_codigo as codigo_articulo, 
ar.art_nome as nombre_rticulo, 
ar.art_peso as peso_articulo, 
pr.prv_nome as nombre_proveedor
from artigos as ar inner join provedores as pr on ar.art_provedor=pr.prv_id
where ar.art_color='negro';
-- Mostrar para cada venta: nombre y apellidos del cliente y fecha de venta.
select
clientes.clt_nome,
clientes.clt_apelidos,
vendas.ven_data as Fecha_venta
from clientes inner join vendas on vendas.ven_id = clientes.clt_vendas;
-- Mostrar: número total de ventas, número de artículos vendidos, suma de unidades vendidas y la media de los precios de los artículos vendidos.
-- Seleccionar para cada artículo su número, nombre, peso y el nombre que corresponde al peso (peso_nome), teniendo en cuenta la información contenida en la tabla pesos, que dá un nombre a los pesos en función del intervalo al que pertenece, Ordenar el resultado por el peso del artículo, de mayor a menor.
-- Seleccionar para cada venta:
-- Datos de la venta: identificar y fecha de la venta.
-- Datos del cliente: nombre del cliente (nombre y apellidos separados por coma).
-- Datos del empleado: nombre del empleado (nombre y apellidos separados por coma). Mostrar los datos ordenados por el apellido y nombre del cliente.
-- Para todos los clientes con identificador menor o igual a 10, seleccionar los datos de las ventas que se hicieron. Hay que mostrar, para cada venta, el identificador del cliente, apellidos, nombre y fecha de venta. Si a alguno de esos clientes no se le hizo ninguna venta, deberá aparecer en el resultado con su identificador, apellidos, nombre y el texto SIN COMPRAS en la columna de fecha de venta.
-- Seleccionar el código (emp_id), apellidos y nombre de todos los empleados. Añadir una columna en el resultado, con el alias ventas, en la que se muestre el literal Si si el empleado hizo alguna venta, o el literal No en el caso de que aún no hiciese ninguna venta.
-- Obtener una lista de todos los artículos que tengan un precio de compra superior al precio de compra del artículo con código 0713242.
-- Seleccionar todos los artículos negros, junto con los artículos que pesan más de 5000 gramos, escribiendo dos consultas y utilizando el operador de unión de consultas.
