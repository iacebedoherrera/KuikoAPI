# API PARA KUIKO

En este proyecto abordaremos la creación de una API desde cero.

## Tabla de Contenidos
1. [Instalación](#instalación)
2. [Ejercicios](#ejercicios)
3. [Contacto](#contacto)


## Instalación

Instrucciones de uso:
1. Clona el repositorio
2. Ejecuta el comando: 
    ./mvnw spring-boot:run
    ```bash
    ./mvnw spring-boot:run
    ```


## Ejercicios

Previo a la ejecución del endpoint, hay que importar los datos de la base de datos en memoria H2. Para ello, llamaremos a los endpoints:
1. /api/import/communities?filePath=src/main/resources/static/COMUNIDAD_AUTONOMA.csv
2. /api/import/provinces?filePath=src/main/resources/static/PROVINCIA.csv
Es importante hacerlo en ese orden, ya que no se pueden importar las provincias sin antes tener las comunidades.

Los endpoints están securizados, por lo que es necesario Basic Auth para acceder a ellos.
Tenemos dos usuarios:
- Username: user, Password: password, Role: USER
- Username: admin, Password: admin, Role: ADMIN


### Ejercicio 1

Desarrollar un endpoint que dado el parámetro código de provincia responda con:
- Código de la comunidad autónoma.
- Nombre de la comunidad autónoma.
- Código de la provincia.
- Nombre de la provincia.


### Ejercicio 2

Desarrollar un endpoint que dado el parámetro código de comunidad autónoma responda con:
- Código de la comunidad autónoma.
- Nombre de la comunidad autónoma.
- Cantidad de provincias.


## Tests

En la clase ApiApplicationTests hay tests unitarios que prueban todos los endpoints (menos los de importación de CSVs).


## Contacto

Prueba realizada por Iván Acebedo Herrera.
Email de contacto: iacebedoherrera@gmail.com