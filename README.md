# LibraryProject

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-9.2-02303A?style=for-the-badge&logo=gradle&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Soportado-2496ED?style=for-the-badge&logo=docker&logoColor=white)

Sistema de gestión de biblioteca escrito en **Java 21** y organizado como un **proyecto Gradle multimódulo**. Cada entidad del dominio (libros, autores, usuarios) vive en su propio módulo, la lógica de negocio se concentra en el módulo `Library` y la aplicación de consola en `app` orquesta las operaciones.

## Descripción

El objetivo del proyecto es practicar la separación de responsabilidades a nivel de build: cada capa es una librería Gradle independiente que declara explícitamente de qué otros módulos depende. La configuración común (versión de Java, tipo de proyecto) se centraliza en **plugins de convención** escritos en Groovy dentro de `gradle/plugins`, de modo que cada `build.gradle` se reduce a unas pocas líneas.

### Funcionalidades

- Alta, búsqueda, edición y eliminación de **libros** (título, autor, año de publicación, ISBN).
- Alta, búsqueda, edición y eliminación de **autores** (nombre, apellido, biografía, libros publicados).
- Alta, búsqueda, edición y eliminación de **usuarios** (username, email, contraseña, libros prestados).
- Validación de duplicados: no se permite registrar dos libros con el mismo título, dos autores con el mismo nombre completo ni dos usuarios con el mismo username o email.
- Manejo de errores centralizado con la excepción personalizada `NotFound`.
- Búsquedas sin distinción de mayúsculas y minúsculas mediante la **Streams API**.

## Tecnologías utilizadas

| Tecnología | Uso |
| --- | --- |
| Java 21 | Lenguaje principal (toolchain configurado por Gradle) |
| Gradle 9.2 (wrapper incluido) | Build multimódulo, `java-library` y `application` |
| Plugins de convención en Groovy | `plugin-personal-java-core`, `plugin-personal-librerias`, `plugin-personal-app` |
| Version catalog (`libs.versions.toml`) | Declaración centralizada de dependencias (Guava, JUnit 5) |
| Docker (`eclipse-temurin:21-jdk`) | Ejecución de la aplicación en contenedor |

## Estructura del proyecto

```text
LibraryProject/
├── app/                 # Aplicación de consola (clase principal com.management.context.Context)
├── Books/               # Modelo Books
├── Authors/             # Modelo Authors (depende de Books)
├── Users/               # Modelo Users (depende de Books)
├── Library/             # Lógica de negocio: CRUD y validaciones (depende de todos los modelos)
├── NotFound/            # Excepción personalizada NotFound
├── gradle/
│   ├── libs.versions.toml
│   └── plugins/java-plugins/src/main/groovy/   # Plugins de convención
├── Dockerfile
└── settings.gradle
```

Grafo de dependencias entre módulos:

```text
app ──► Library ──► Books, Authors, Users, NotFound
        Authors ──► Books
        Users   ──► Books
```

## Instalación y uso

### Requisitos

- JDK 17 o superior para ejecutar Gradle. El toolchain de Java 21 se descarga automáticamente si no está instalado (`org.gradle.java.installations.auto-download=true`).
- Git.

### Ejecutar con Gradle

```bash
git clone https://github.com/Donaldo500/LibraryProject.git
cd LibraryProject

# Linux / macOS
./gradlew :app:run

# Windows
gradlew.bat :app:run
```

### Ejecutar con Docker

```bash
docker build -t library-project .
docker run --rm library-project
```

## Ejemplos de uso

La clase `Context` del módulo `app` muestra el flujo completo:

```java
Library library = new Library();

library.addBook(new Books("El sutil arte de que te importe un carajo", "Mark Manson", 2016, "978-0743273565"));
library.addAuthor(new Authors("Mark", "Manson", "Un gran autor que cambia tu perspectivas"));
library.addUser(new Users("john_doe", "john@example.com", "password123"));

Books libro = library.findBook("El sutil arte de que te importe un carajo");
library.editBook(libro.getTitle(), "Titulo falso", "Autor falso", 2020, "123");
library.deleteBook("Titulo falso");
```

Salida real al ejecutar `./gradlew :app:run`:

```text
Se agrego el libro: El sutil arte de que te importe un carajo a la biblioteca.
Se agrego el autor: Mark Manson
Se agrego el usuario: john_doe.
Libro encontrado: El sutil arte de que te importe un carajo por Mark Manson
Autor encontrado: Mark Manson
Usuario encontrado: john_doe con email: john@example.com
El sutil arte de que te importe un carajo se ha editado.
El autor: Mark Manson se ha editado.
El usuario: john_doe se ha editado.
Titulo falso se ha eliminado.
Autor falso se ha eliminado.
john_new se ha eliminado.
```

Si se intenta registrar un elemento duplicado o buscar uno inexistente, se lanza `NotFound` con un mensaje descriptivo, por ejemplo:

```text
Exception in thread "main" com.layer.notfound.NotFound: No se encontro el libro: Titulo falso
```

## Contribuciones

Este es un proyecto individual con fines de aprendizaje. Las sugerencias son bienvenidas:

1. Haz un fork del repositorio.
2. Crea una rama: `git checkout -b mejora/nombre-de-la-mejora`.
3. Realiza tus cambios y haz commit.
4. Abre un pull request describiendo el cambio.

## Autor

**Donaldo Ibarra** - [@Donaldo500](https://github.com/Donaldo500)
