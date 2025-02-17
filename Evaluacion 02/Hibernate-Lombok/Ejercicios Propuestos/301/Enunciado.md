Ejercicio 301
Desarrolla en Java un programa que utilizando Hibernate permita crear una clase 
base de datos llamada empleados la cual permita representar los empleados (Emp) que hay en un departamento (Depto) sabiendo que:

un departamento está formado por varios empleados
un empleado solo puede estar en un departamento.
Además, para la tabla de departamentos (Depto) se quiere almacenar:

id de departamento que será auto incremental
nombre del departamento
localidad
Por otro lado, para la tabla de empleados (Emp) se quiere almacenar:

id del empleado que será auto incremental
nombre
puesto
sueldo
edad
DNI
esJefe
Crea una clase App que permita crear 5 departamentos y 10 empleados de tal forma que cada departamento tenga 2 empleados.