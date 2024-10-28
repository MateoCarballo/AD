/*
Sobre la base de datos anterior se pide realizar las siguientes consultas básicas:
		x Mostrar los datos de todas las tiendas.
		x Mostrar los nombres de todos los proveedores.
		x Obtener la lista de las poblaciones en las que existan clientes.
		x Mostrar el precio de venta de todos los artículos y el precio que resultaría después de aplicarles un incremento del 10%.
		x Mostrar el número de cliente, apellidos y nombre de todos los clientes de Madrid.
		x Seleccionar el código, descripción y peso de los artículos que pesen más de 500 gramos.
		x Seleccionar todos los artículos que tengan un precio de venta superior o igual al doble del precio de compra.
		x Seleccionar apellidos, nombre, población y descuento de todos los clientes de Asturias o Valencia que tengan un descuento superior al 2% o que no tengan descuento.
		x Seleccionar todos los artículos de color negro que pesen más de 5000 gramos.
		x Obtener todos los artículos que no son de color negro o que tienen un peso menor o igual de 5000 gramos.
		x Seleccionar los artículos que son de color negro y pesan más de 100 gramos, o bien son de color cyan.
		x Hacer una lista de los artículos que tienen un precio de compra de entre 12 y 18 euros, ambos precios incluidos.
		x Mostrar una lista de artículos de color negro o color cyan.
		x Buscar un cliente del que se desconoce el apellido exacto, pero se sabe que las dos primeras letras son RO.
		x Buscar clientes que tengan el nombre de 5 letras, empezando por B y terminando por A.
		x Buscar todos los artículos para los que no se guardó su color.
		x Clasificar los artículos teniendo en cuenta su peso, por orden descendente.
		x Mostrar el código de artículo, nombre, precio de compra, precio de venta y margen de beneficio (precio de venta – precio de compra) de los artículos que tienen un precio de compra superior a 3000 euros, ordenados por el margen.
		x Clasificar nombre, proveedor, stock y peso de los artículos que tienen un peso menor o igual a 1000 gramos, por orden ascendente del proveedor. Cuando los proveedores coincidan, deben clasificarse por el stock en orden descendente.
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
-- Seleccionar todos los artículos que tengan un precio de venta superior o igual al doble del precio de compra.
SELECT art_nome AS nombre, art_pc AS precio_compra, art_pv AS precio_venta FROM  artigos WHERE (art_pv > (2*art_pc));
-- Seleccionar apellidos, nombre, población y descuento de todos los clientes de Asturias o Valencia que tengan un descuento superior al 2% o que no tengan descuento.
SELECT 
	clt_apelidos AS apellidos,
    clt_nome AS nombre,
    clt_poboacion AS poblacion,
    clt_desconto AS descuento
FROM
	clientes
WHERE
	( clt_poboacion = "Asturias" 
    OR
    clt_poboacion = "Valencia" )
    AND
    (clt_desconto > 2
    OR
    clt_desconto = 0);
-- Seleccionar todos los artículos de color negro que pesen más de 5000 gramos.
SELECT 
	*
FROM 
	artigos
WHERE
	art_peso > 500
    AND 
    art_color = 'NEGRO';
    
-- Obtener todos los artículos que no son de color negro o que tienen un peso menor o igual de 5000 gramos.
SELECT 
	*
FROM 
	artigos
WHERE
	(art_peso < 500
    OR 
    art_color <> 'NEGRO');

-- Seleccionar los artículos que son de color negro y pesan más de 100 gramos, o bien son de color cyan.
SELECT 
	*
FROM
	artigos
WHERE
	(art_color = 'NEGRO'
    AND
    art_peso > 100)
    OR 
    art_color = 'CYAN';

-- Hacer una lista de los artículos que tienen un precio de compra de entre 12 y 18 euros, ambos precios incluidos.
SELECT 
	*
FROM
	artigos
WHERE
	(art_pc >=  12
    AND
    art_pc <=  18);
    
-- Mostrar una lista de artículos de color negro o color cyan.    
SELECT
	*
FROM
	artigos
WHERE
	(art_color = 'NEGRO'
    OR
    art_color = 'CYAN');

-- Buscar un cliente del que se desconoce el apellido exacto, pero se sabe que las dos primeras letras son RO.
SELECT
	*
FROM
	clientes
WHERE
	clt_nome LIKE 'Ro%';

-- Buscar clientes que tengan el nombre de 5 letras, empezando por B y terminando por A.
 select
	*
from
	clientes
where
	clt_nome regexp '^B.{3}A$';

-- Buscar todos los artículos para los que no se guardó su color.

SELECT 
	*
FROM
	artigos
WHERE
    art_color is null;

-- Clasificar los artículos teniendo en cuenta su peso, por orden descendente.

select
	* 
from
    artigos
order by
	art_peso
DESC;

-- Mostrar el código de artículo, nombre, precio de compra, precio de venta y margen de beneficio (precio de venta – precio de compra) de los artículos que 
-- tienen un precio de compra superior a 3000 euros, ordenados por el margen.

select
	art_codigo as CODIGO,
    art_nome as NOMBRE,
    art_pc as PRECIO_COMPRA,
	art_pv as PRECIO_VENTA,
    (art_pv - art_pc) as BENEFICIO
from
	artigos
where
	art_pc > 3000
order by
	BENEFICIO
DESC;
	
/* Clasificar nombre, proveedor, stock y peso de los artículos que tienen un peso menor o igual a 1000 gramos, 
por orden ascendente del proveedor. Cuando los proveedores coincidan, deben clasificarse por el stock en orden descendente.
*/

select 
	art_nome as Nombre,
    art_provedor as Proveedor,
    art_stock as Stock,
    art_peso as Peso
from 
	artigos
where
	art_peso <= 1000
order by 
	Proveedor,
    Stock
DESC;

-- Seleccionar nombre y apellidos de los clientes que tengan un apellido que empiece por F y termine por Z.

select
	clt_nome as Nombre,
    clt_apelidos as Apellidos
from
	clientes
where
	clt_apelidos like '% F%Z'
    or
    clt_apelidos like 'F%Z %';

-- Seleccionar los clientes cuyo primer apellido empiece por la letra a o por la letra f.

select
	* 
from
	clientes
where 
	clt_apelidos like 'A%'
    or
    clt_apelidos like 'F%';


-- Seleccionar los clientes cuyo nombre no empiece por a. 

select
	*
from
	clientes
where
	clt_nome not like 'A%'
order by
	clt_nome;

-- Seleccionar los clientes que tengan un nombre que tenga exactamente 5 caracteres.

select 
	*
from
	clientes
where
	char_length(clt_nome) = 5;