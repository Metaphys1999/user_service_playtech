# Proyecto de Prueba Vacante Spring Boot

## Descripción del Proyecto

Este proyecto de prueba vacante está desarrollado en Spring Boot y aborda varios aspectos esenciales. A continuación, se detallan los elementos clave del proyecto:

### Extras Deseables:

1. **Copia de Código en GitHub:**
   - El proyecto se encuentra disponible en el repositorio de GitHub: [user_service_playtech](https://github.com/Metaphys1999/user_service_playtech).
   - Se han creado dos ramas:
     - `main`: Contiene la inicialización del proyecto con las dependencias iniciales obtenidas a través de [Spring Initializer](https://start.spring.io/).
     - `develop`: Incluye todos los cambios realizados sin afectar la rama `main`. Se abordan aspectos como seguridad, pruebas unitarias, manejo de excepciones, DTO, Mapper, Controller, Service e Implementación, entidad, repository, y la implementación de Swagger UI por dependencia. También se contempla el manejo del archivo `.yml` y el uso de PostgreSQL.

2. **Despliegue en Servidor Online Gratuito:**
   - La aplicación ha sido desplegada en un servidor online gratuito en [Fly.io](https://fly.io/).

3. **Test Unitarios:**
   - Se han implementado test unitarios utilizando JUnit para las capas de JPA, JPQL, Repository y Service.

### Consideraciones:

- **Configuración .yml:**
  - En el archivo `.yml`, se inicialmente consideró el uso de un puerto dinámico. Sin embargo, se decidió dejar el puerto estático en 8080 en lugar de usar el número 0 para un puerto dinámico.

- **Base de Datos:**
  - La base de datos utilizada se denomina `userbd`. Se proporciona un archivo `.sql` en la misma carpeta, el cual contiene usuarios de prueba. Esto facilita la realización de pruebas sin necesidad de registrar nuevos usuarios, permitiendo un inicio rápido con la autenticación a través de los endpoints protegidos.
