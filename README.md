# Products API

Este proyecto es un **microservicio** desarrollado con **Spring Boot**, basado en **Clean Architecture** y utilizando **JDBC** para la persistencia de datos. Implementa una separación clara de responsabilidades en `application`, `domain` e `infrastructure`, además de contar con el uso de otras tecnologías y herramientas para optimización del rendimiento,la escalabilidad y mantenibilidad a largo plazo.

## Arquitectura

El proyecto sigue los principios de **Clean Architecture**, organizando el código en capas:
- **Application**: Contiene los casos de uso y la lógica de aplicación.
- **Domain**: Define los modelos de negocio y sus reglas.
- **Infrastructure**: Implementa la persistencia, controladores y configuraciones.

Además, forma parte de un ecosistema de **microservicios**, facilitando la escalabilidad y mantenibilidad.

## Tecnologías utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Cache con Caffeine**
- **SpringDoc OpenAPI (Swagger)**
- **Jacoco**
- **SonarQube**

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

  ### Tabla de Request y Respuestas del Endpoint `/products/prices`

#### Request

| Parámetro   | Descripción                                      | Requerido | Tipo    | Formato   |
|-------------|--------------------------------------------------|-----------|---------|-----------|
| `date`      | Fecha en formato `YYYY-MM-DD`                    | Sí        | string  | date      |
| `productId` | Identificador del producto                       | Sí        | integer | -         |
| `brandId`   | Identificador de la marca                        | Sí        | integer | -         |

#### Respuestas

| Código | Descripción                                         | Contenido                                                                                   |
|--------|-----------------------------------------------------|---------------------------------------------------------------------------------------------|
| `200`  | Datos obtenidos exitosamente                       | `application/json`: Devuelve un objeto `Product` con los detalles del producto y precio.     |
| `400`  | Parámetros inválidos o no existentes               | `application/json`: Mensaje de error detallando el problema con los parámetros.             |
| `404`  | No se encontró el precio del producto con el ID    | `application/json`: Mensaje de error indicando que no se encontró el producto con ese ID.   |
| `500`  | Ocurrió un error inesperado                        | `application/json`: Mensaje de error general indicando que ocurrió un error en el servidor. |

#### Ejemplo de Respuesta (`200`)

```json
{
  "product_id": 35455,
  "brand_id": 1,
  "price_list": 1,
  "start_date": "2020-06-14T00:00:00Z",
  "end_date": "2020-12-31T23:59:59Z",
  "price": "38.95",
  "curr": "EUR"
}
```

## Justificación del uso de teconologías, patrones, mejoras, etc.

### Uso de Swagger

**Swagger** (OpenAPI) se utiliza para:

- **Documentación interactiva**: Permite explorar y probar los endpoints de la API de manera visual.
- **Estandarización**: Sigue el estándar **OpenAPI**, facilitando la integración con otras herramientas.
- **Facilita la comunicación**: Ayuda a los desarrolladores a entender rápidamente la API y sus endpoints.
- **Generación automática**: Actualiza la documentación automáticamente desde el código, asegurando consistencia.

El archivo YAML se muestra porque:

- **Estandarización**: Describe la API de manera estructurada, fácil de entender y mantener.
- **Integración con herramientas**: Facilita la generación de documentación interactiva y pruebas automatizadas.
- **Mantenimiento centralizado**: Simplifica las actualizaciones de la API y su documentación sincronizada.

Swagger y el archivo YAML proporcionan una documentación accesible, precisa y siempre actualizada.

Puedes acceder a la documentación interactiva en `http://localhost:8080/api`.

### **Java 17**

- **Soporte LTS (Long-Term Support)**: Garantiza actualizaciones y parches de seguridad a largo plazo.
- **Mejor rendimiento**: Optimización de la recolección de basura y mayor eficiencia en el JIT.
- **Compatibilidad**: Fácil migración desde versiones anteriores sin comprometer la estabilidad.

### **Spring Boot**

- **Desarrollo rápido**: Configuración automática que acelera la creación de aplicaciones.
- **Ideal para microservicios**: Facilita la creación de aplicaciones escalables y mantenibles.
- **Integración con el ecosistema de Spring**: Ofrece herramientas integradas como Spring Data, Spring Security y Spring Cloud.
- **Configuración mínima**: Reduce la necesidad de configuración manual, lo que agiliza el proceso de desarrollo.

#### Conclusión

**Java 17** ofrece estabilidad, rendimiento y nuevas características, mientras que **Spring Boot** simplifica y acelera el desarrollo de aplicaciones escalables y robustas, haciendo de ambas tecnologías una excelente elección para proyectos a largo plazo.


### Spring Cache con Caffeine

Este microservicio usa Caffeine para cachear datos y mejorar el rendimiento debido a las multiples llamadas que puede recibir el endpoint siendo algunas con valores repetitivos. Puedes modificar los tiempos de expiración en `application.properties`. Por ejemplo:
```
cache.expiration=3600
```

### Uso de H2 en memoria

La base de datos **H2 en memoria** se ha utilizado en este proyecto por varias razones:

- **Ligereza**: H2 es una base de datos muy ligera, lo que la hace ideal para entornos de desarrollo o pruebas.
- **Facilidad de configuración**: No requiere un servidor externo, lo que facilita la puesta en marcha y configuración en el entorno local.
- **Rendimiento**: Al ser en memoria, los datos no se almacenan en disco, lo que reduce la sobrecarga de operaciones de lectura y escritura, lo que es especialmente útil para pruebas.
- **Compatibilidad con PostgreSQL**: H2 se ha configurado en **modo PostgreSQL**, lo que asegura que las consultas y características de la base de datos sean similares a las que se esperarían en un entorno de producción con PostgreSQL.

### Justificación del índice en la BDD

Se añadió un **índice** a la tabla **PRICES** para mejorar el rendimiento de las consultas. Esto se debe a lo siguiente:

- **Consultas frecuentes**: Las consultas más comunes en este endpoint involucran la búsqueda de precios de productos basados en **PRODUCT_ID**, **BRAND_ID** y un rango de fechas (definido por **START_DATE** y **END_DATE**).
- **Optimización del rendimiento**: Sin un índice, la base de datos tendría que recorrer todas las filas de la tabla para encontrar los registros relevantes, lo que sería ineficiente a medida que la tabla crece. Al crear un índice en las columnas más utilizadas para las consultas (como **PRODUCT_ID**, **BRAND_ID**, y **START_DATE**), se mejora la velocidad de acceso a los datos y se reduce el tiempo de respuesta.
- **Escalabilidad**: A medida que el volumen de datos aumenta, el índice asegura que el rendimiento no se vea comprometido, manteniendo la eficiencia en las consultas.


### Uso de Spring Data JPA

**JPA (Java Persistence API)** se ha utilizado en este proyecto para gestionar la persistencia de datos de manera eficiente. Las principales razones para su uso son:

- **Abstracción de la base de datos**: JPA proporciona una capa de abstracción sobre la base de datos, lo que permite interactuar con las tablas de manera orientada a objetos. Esto facilita la escritura de consultas y operaciones sin preocuparse por los detalles específicos de la base de datos subyacente.
- **Integración con Spring Data JPA**: Spring Data JPA simplifica el trabajo con repositorios, eliminando la necesidad de escribir implementaciones complejas para las operaciones CRUD (crear, leer, actualizar, eliminar). Esto mejora la mantenibilidad del código.
- **Transparencia y facilidad de uso**: JPA maneja de forma automática muchas de las complejidades asociadas con la persistencia, como el manejo de transacciones, la sincronización de objetos con la base de datos, y la gestión del ciclo de vida de las entidades.
- **Compatibilidad con el ecosistema Spring**: JPA se integra de forma nativa con Spring Boot, lo que facilita la configuración y gestión de las entidades, repositorios y transacciones, permitiendo un desarrollo ágil y eficiente.

### Uso del patrón Repository

El patrón **Repository** se utiliza para separar la lógica de acceso a datos de la lógica de negocio. Sus principales beneficios son:

- **Abstracción de la persistencia**: El patrón Repository actúa como una capa de acceso a datos que oculta los detalles de la implementación de la base de datos, proporcionando una interfaz más limpia y orientada a objetos para interactuar con los datos.
- **Facilidad de mantenimiento**: Al centralizar las operaciones de persistencia en un repositorio, cualquier cambio relacionado con la base de datos (por ejemplo, un cambio en la consulta o la estructura de la base de datos) se puede manejar en un solo lugar, lo que facilita el mantenimiento y reduce la duplicación de código.
- **Testabilidad**: Al usar repositorios, es más fácil crear mocks o stubs para las pruebas unitarias, ya que la lógica de acceso a datos está aislada de la lógica de negocio.

En el contexto de este proyecto, los repositorios son utilizados para interactuar con la tabla **PRICES**, permitiendo realizar consultas y actualizaciones sin acoplarse directamente a la implementación de JPA.

### Uso del patrón Mother para los tests

El patrón **Mother** se ha utilizado en los tests para crear instancias de objetos de manera sencilla y controlada. Este patrón es útil para mejorar la calidad de las pruebas de las siguientes maneras:

- **Reducción de código repetitivo**: Los objetos de prueba suelen requerir configuraciones similares para las pruebas unitarias (por ejemplo, la creación de una entidad `Product` con ciertos valores predeterminados). El patrón Mother permite centralizar la creación de estos objetos, lo que evita la repetición de código y mejora la legibilidad de los tests.
- **Control sobre los datos de prueba**: Al usar el patrón Mother, los datos de prueba se pueden crear de manera más flexible y controlada. Esto permite que se generen diferentes variaciones de los objetos según las necesidades de las pruebas, sin tener que escribir múltiples instancias de creación en cada test.
- **Mejor organización y claridad**: El patrón Mother ayuda a mantener las pruebas organizadas y más fáciles de entender. Al abstraer la creación de objetos complejos, los tests se enfocan en las pruebas de lógica y comportamiento en lugar de detalles de configuración.

### Uso de JaCoCo

**JaCoCo** es una herramienta de cobertura de pruebas que permite medir qué porcentaje del código ha sido cubierto por las pruebas unitarias. Se utiliza en este proyecto para:

- Medir la efectividad de las pruebas.
- Identificar áreas del código no cubiertas.
- Generar informes de cobertura en formatos como HTML y XML.
- Mejorar la calidad del código al optimizar las pruebas existentes.

#### Integración con SonarQube

**SonarQube** realiza un análisis estático de calidad del código y, cuando se integra con JaCoCo, proporciona:

- Visualización de la cobertura de pruebas en el dashboard de SonarQube.
- Un análisis completo de la calidad del código y las métricas de cobertura.
- Alertas sobre baja cobertura o áreas no cubiertas que necesitan pruebas.
- Seguimiento continuo de la calidad en el pipeline de integración continua.
  
### Uso de SonarQube

**SonarQube** se ha integrado en este proyecto como una herramienta de análisis estático de código para asegurar la calidad del software. Sus principales beneficios incluyen:

- **Análisis de calidad del código**: SonarQube ayuda a detectar errores, vulnerabilidades, malas prácticas y code smells en el código fuente, lo que contribuye a mantener un código limpio, legible y sostenible.
- **Métricas de calidad**: SonarQube proporciona métricas detalladas sobre el código, como la cobertura de pruebas, la complejidad ciclómica y las duplicaciones. Estas métricas son esenciales para monitorear la salud del proyecto a lo largo del tiempo.
- **Integración continua**: SonarQube se integra fácilmente en los pipelines de integración continua, lo que permite que cada cambio en el código sea analizado automáticamente, garantizando que las nuevas implementaciones no introduzcan problemas en el proyecto.
- **Mejora continua**: El análisis regular con SonarQube permite identificar áreas del código que necesitan refactorización, lo que fomenta la mejora continua en el desarrollo del software.

#### Cómo ejecutar SonarQube en el proyecto

Para analizar el proyecto con SonarQube, primero debes tener **SonarQube** ejecutándose localmente. [SonarQube](https://www.sonarqube.org/downloads/).
Puedes levantar la imagen de **SonarQube** con docker para el desarrollo en entorno local.
Una vez que SonarQube esté inciado , puedes ejecutar el análisis del proyecto utilizando el siguiente comando en la terminal:

```sh
./gradlew sonar -D"sonar.projectKey=TEST" -D"sonar.projectName=." -D"sonar.host.url=http://localhost:9000" -D"sonar.token=sqp_e8e0056a3f641e67a569e102b811bef1d7752a1d"
```

Ejemplo de resultado en SonarQube:

![Descripción de la imagen](https://github.com/Elkiin-dev/products/blob/main/src/main/resources/img/ResultadoSonarQube.PNG?raw=true)


