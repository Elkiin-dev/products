# Products API

Este proyecto es un **microservicio** desarrollado con **Spring Boot**, basado en **Clean Architecture** y utilizando **JDBC** para la persistencia de datos. Implementa una separación clara de responsabilidades en `application`, `domain` e `infrastructure`, además de contar con cache para optimización del rendimiento.

## Arquitectura

El proyecto sigue los principios de **Clean Architecture**, organizando el código en capas:
- **Application**: Contiene los casos de uso y la lógica de aplicación.
- **Domain**: Define los modelos de negocio y sus reglas.
- **Infrastructure**: Implementa la persistencia, controladores y configuraciones.

Además, forma parte de un ecosistema de **microservicios**, facilitando la escalabilidad y mantenibilidad.

## Tecnologías utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Data JDBC**
- **Spring Cache con Caffeine**
- **SpringDoc OpenAPI (Swagger)**
- **Gradle**

## Instalación y ejecución

### 1. Clonar el repositorio
```sh
git clone https://github.com/Elkiin-dev/products.git
cd products
```

### 2. Construir el proyecto
```
./gradlew build
```
### 3. Ejecutar la aplicación
```
./gradlew bootRun
```
La API estará disponible en `http://localhost:8080`.

##  Endpoint principal

- `GET /products/prices` - Obtener el precio de los productos

## Documentación API

Puedes acceder a la documentación interactiva en `http://localhost:8080/api`.

## Configuración de Cache

Este microservicio usa Caffeine para cachear datos y mejorar el rendimiento. Puedes modificar los tiempos de expiración en `application.properties`.

## Mejoras
- Se puede añadir un índices a la tabla **`PRICES`** de la base de datos para mejorar el rendimiento de la consultas.
