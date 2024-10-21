/*
Sobre la base de datos anterior se pide realizar las siguientes consultas básicas:
		Mostrar los datos de todas las tiendas.
		Mostrar los nombres de todos los proveedores.
		Obtener la lista de las poblaciones en las que existan clientes.
		Mostrar el precio de venta de todos los artículos y el precio que resultaría después de aplicarles un incremento del 10%.
		Mostrar el número de cliente, apellidos y nombre de todos los clientes de Madrid.
		Seleccionar el código, descripción y peso de los artículos que pesen más de 500 gramos.
		Seleccionar todos los artículos que tengan un precio de venta superior o igual al doble del precio de compra.
		Seleccionar apellidos, nombre, población y descuento de todos los clientes de Asturias o Valencia que tengan un descuento superior al 2% o que no tengan descuento.
		Seleccionar todos los artículos de color negro que pesen más de 5000 gramos.
		Obtener todos los artículos que no son de color negro o que tienen un peso menor o igual de 5000 gramos.
		Seleccionar los artículos que son de color negro y pesan más de 100 gramos, o bien son de color cyan.
		Hacer una lista de los artículos que tienen un precio de compra de entre 12 y 18 euros, ambos precios incluidos.
		Mostrar una lista de artículos de color negro o color cyan.
		Buscar un cliente del que se desconoce el apellido exacto, pero se sabe que las dos primeras letras son RO.
		Buscar clientes que tengan el nombre de 5 letras, empezando por B y terminando por A.
		Buscar todos los artículos para los que no se guardó su color.
		Clasificar los artículos teniendo en cuenta su peso, por orden descendente.
		Mostrar el código de artículo, nombre, precio de compra, precio de venta y margen de beneficio (precio de venta – precio de compra) de los artículos que tienen un precio de compra superior a 3000 euros, ordenados por el margen.
		Clasificar nombre, proveedor, stock y peso de los artículos que tienen un peso menor o igual a 1000 gramos, por orden ascendente del proveedor. Cuando los proveedores coincidan, deben clasificarse por el stock en orden descendente.
		Seleccionar nombre y apellidos de los clientes que tengan un apellido que empiece por F y termine por Z.
		Seleccionar los clientes cuyo primer apellido empiece por la letra a o por la letra f.
		Seleccionar los clientes cuyo nombre no empiece por a. 
		Seleccionar los clientes que tengan un nombre que tenga exactamente 5 caracteres.
*/

USE tenda;
-- Mostrar los datos de todas las tiendas.
SELECT * FROM tendas;
-- Mostrar los nombres de todos los proveedores.
SELECT prv_nome FROM provedores;
-- Obtener la lista de las poblaciones en las que existan clientes.
SELECT DISTINCT clt_poboacion FROM clientes;
-- Mostrar el precio de venta de todos los artículos y el precio que resultaría después de aplicarles un incremento del 10%.
SELECT art_nome AS nombre, art_pv AS precio, (art_pv * 1.1) AS precio_resultado from artigos order by nombre; 
-- Mostrar el número de cliente, apellidos y nombre de todos los clientes de Madrid.
SELECT clt_id as cliente , clt_nome AS nombre, clt_apelidos AS apellidos from clientes where clt_poboacion = "Madrid";
-- Seleccionar el código, descripción y peso de los artículos que pesen más de 500 gramos.
SELECT art_codigo AS codigo, art_nome AS nombre, art_peso AS peso from artigos where art_peso > 500 ORDER BY peso;

