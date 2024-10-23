# API PARA KUIKO

En este proyecto abordaremos la creación de una API desde cero.

## Tabla de Contenidos
1. [Instalación](#instalación)
2. [Ejercicios](#ejercicios)
3. [Contacto](#contacto)


## Instalación

Instrucciones de uso:
1. Clona el repositorio en tu local:
    ```bash
    git clone https://github.com/iacebedoherrera/KuikoAPI.git
    ```
2. Ejecuta los comandos: 
    ```bash
    ./mvnw clean install
    ```
    ```bash
    ./mvnw spring-boot:run
    ```

Requisitos mínimos:
- Java Version: 17
- Spring Boot Version: 3.3.4



## Ejercicios

Previo a la ejecución de los endpoints, hay que importar los datos de la base de datos en memoria H2. Para ello, llamaremos a los endpoints:
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

### Ejercicio 3

En este tercer ejercicio, en el que se pedía comunicar con una API de terceros, se ha decidido crear un nuevo endpoint que hace lo siguiente:

a partir del código de una comunidad autónoma de España, te devuelve el tiempo actual en la capital de dicha comunidad autónoma.

El endpoint devuelve 4 datos:
- Código de comunidad autónoma
- Temperatura (en ºC).
- Presión atmosférica (en hPa).
- Humedad (en %).

Para devolver estos datos, hemos hecho 2 llamadas a la API de OpenWeatherMap.

La primera para obtener los datos de longitud y latitud de una provincia (capital de la comunidad autónoma).

Y la segunda llamada, que utiliza estos dos datos para obtener el tiempo actual en dicha localización.


## Tests

En la clase ApiApplicationTests hay tests unitarios que prueban todos los endpoints (menos los de importación de CSVs).


## Contacto

Prueba realizada por Iván Acebedo Herrera.

Email de contacto: iacebedoherrera@gmail.com