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
- código de la comunidad autónoma.
- nombre de la comunidad autónoma.
- código de la provincia.
- nombre de la provincia.


## Contacto

Prueba realizada por Iván Acebedo Herrera.
Email de contacto: iacebedoherrera@gmail.com