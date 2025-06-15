# Sistema de Gestión de Hospitales (SQLite + JDBC)

Este proyecto es una implementación académica de un sistema de gestión de hospitales. Forma parte de una actividad práctica en la que se desarrollaron funcionalidades CRUD para dos tablas: `medico` y `empleado`. Todo el sistema está desarrollado en Java utilizando JDBC clásico y SQLite, sin frameworks de persistencia como Hibernate.

## Objetivo del proyecto

- Implementar las operaciones básicas de una base de datos de hospital (insertar, consultar, actualizar, eliminar).
- Utilizar una arquitectura por capas, bajo acoplamiento y separación de responsabilidades.
- Aplicar pruebas unitarias con JUnit 4 para cada operación CRUD.
- Ejecutar todo el flujo desde una clase `Main`.


## Requisitos previos

- Java 17 o superior
- Maven 3.6 o superior

## Compilar el proyecto

Para compilar el proyecto desde consola:

    mvn clean compile

Esto descargará las dependencias y compilará todo el código fuente.

## Ejecutar el sistema

Opción 1: Ejecutar directamente desde Maven

    mvn exec:java -Dexec.mainClass="co.edu.udec.taskmgr.Main"

Opción 2: Crear un JAR ejecutable

    mvn clean package
    java -jar target/taskmgr-1.0-jar-with-dependencies.jar

Esto ejecutará el sistema completo: inicialización de la base de datos, operaciones CRUD en consola para médicos y empleados.

## Ejecutar las pruebas unitarias

    mvn test

Este comando ejecuta las pruebas unitarias definidas para MedicoRepositoryImpl y EmpleadoRepositoryImpl.

## Resultado esperado

- Se crea la base de datos local SQLite con las tablas `medico` y `empleado`.
- Se insertan datos de ejemplo desde la clase Main.
- Se muestran en consola los registros recuperados, actualizados y eliminados.
- Las pruebas confirman que las operaciones CRUD funcionan correctamente.

## Créditos

Proyecto desarrollado por Daniel y Oscar como parte de una actividad académica de la Universidad de Cartagena.


##  Cómo empezar

1. Clona este repositorio  
   ```bash
   git clone https://github.com/tuusuario/nombre-del-repo.git
   cd nombre-del-repo
