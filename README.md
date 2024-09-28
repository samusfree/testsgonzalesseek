# Mi solución para el Test de [Seek](https://seekglobal.co/) 💪

### Indice

- [Descripción](#descripción)

- [Features](#features)

- [Todo](#todo)

- [Technologies & Tools](#-technologies--tools)

- [Run on local](#run-on-local)

- [Test evidences](#test-evidences)

- [Authors](#authors)

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## Descripción

<p>
Desarrolle una aplicación que exponga una API RESTful de creación de candidatos.

![Descripcion del challange](readme-files/descripcion.png)
</p>


![Componentes de la APP](readme-files/diagrama.png)


## Features

✅ `Feature 1:` CRUD Candidatos

✅ `Feature 2:` Autenticacion JWT

✅ `Feature 3:` Login

## Todo

☑️ `Feature 1:` Pipeline instalación Nube

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## 🛠 Technologies & Tools

- **Language:** Java 21 GraalVM
- **Framework :** Spring Boot 3
- **Architecture :** Layered Architecture
- **Web framework :** Spring REST
- **Data framework :** Spring Data Mongo
- **Database :** Mongo
- **Api Docs :** Spring Doc
- **Container :** Docker and Docker compose

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Git](https://img.shields.io/badge/-Git-F05032?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/-GitHub-181717?style=for-the-badge&logo=github)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## Run on local

1. Clonar el repo
2. Instalar java 21 (con SDK Man o desde instalador)
3. Dentro del repo en la consola ejecutar `./gradlew test` para correr los unit test del proyecto (macos / linux) / `gradlew.bat test` (windows)
4. Dentro del repo en la consola ejecutar `./gradlew spring-boot:run` para iniciar la APP / `gradlew.bat spring-boot:run` (windows). El proyecto corre por defecto en el puerto 8080.
5. Usar la colección de Postman para probar [Postman collection](readme-files/seek-test-sgonzales.postman_collection.json) dentro de el esta el endpoint para probar y los ejemplos para probar los casos de excepción.
6. Asimismo se puede acceder al swagger-ui [Swagger UI](http://localhost:8080/swagger-doc/swagger-ui/index.html)

## Test evidences

![Swagger UI test](readme-files/test_swagger.png)
![Postman test](readme-files/test_postman.png)
![Jacoco report](readme-files/test_jacoco.png)

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## Authors

| [<img src="https://avatars.githubusercontent.com/u/6700707?v=4" width=115><br><sub>Samuel Gonzales</sub>](https://github.com/samusfree) |  
|:---------------------------------------------------------------------------------------------------------------------------------------:|