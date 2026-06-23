# Sistema de Gestión de Préstamos (Backend)

Este es el backend de una aplicación de gestión de préstamos construida con **Spring Boot 3**, siguiendo los principios de **Arquitectura Limpia** y utilizando tecnologías modernas para asegurar escalabilidad, seguridad y rendimiento.

## 🚀 Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.2.5**
- **Spring Data JPA** (Persistencia)
- **Spring Security** (Autenticación y Autorización RBAC)
- **Spring WebFlux** (Manejo reactivo en controladores)
- **MySQL** (Base de Datos Relacional)
- **EHCACHE / JCache** (Sistema de Caché)
- **Hibernate Validator** (Validación de datos)
- **Lombok** (Reducción de código repetitivo)
- **JUnit 5 / Mockito** (Pruebas unitarias)

## 🏗️ Arquitectura

El proyecto sigue un patrón de **Arquitectura Limpia (Clean Architecture)** para separar las responsabilidades:

- **Domain**: Contiene las entidades de negocio, reglas y definiciones de repositorios (interfaces).
- **Application**: (Integrada en los servicios de dominio en esta versión simplificada) Contiene la lógica de uso.
- **Infrastructure**: Implementaciones concretas de repositorios (JPA/MySQL), controladores (API REST), configuraciones (Seguridad, Caché) y manejo de excepciones.

## 📋 Requisitos Previos

Antes de ejecutar la aplicación, asegúrate de tener instalado:

1.  **Java JDK 17** o superior.
2.  **Maven 3.x**.
3.  **MySQL Server** (u otro compatible).

## 🔧 Configuración

1.  **Java JDK**:
    Asegúrate de tener instalado Java 17 o superior y que la variable de entorno `JAVA_HOME` apunte a la carpeta de instalación del JDK.

    **Si recibes el error `JAVA_HOME is set to an invalid directory`**:
    - **En Windows**:
      1. Busca "Variables de entorno" en el menú inicio.
      2. En "Variables del sistema", haz clic en "Nueva".
      3. Nombre: `JAVA_HOME`, Valor: La ruta a tu JDK (ejemplo: `C:\Program Files\Java\jdk-17`).
      4. Busca la variable `Path`, edítala y añade `%JAVA_HOME%\bin`.
      5. Reinicia tu terminal.

2.  **Base de Datos**:
    - Crea una base de datos en MySQL llamada `loan_db`.
    - Ajusta las credenciales en el archivo `src/main/resources/application.properties`:
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/loan_db
      spring.datasource.username=tu_usuario
      spring.datasource.password=tu_contraseña
      ```

2.  **Caché**:
    - La configuración de EHCACHE se encuentra en `src/main/resources/ehcache.xml`. Se utiliza para optimizar las consultas repetitivas sobre el estado de los préstamos.

## 🏃 Ejecución en Local

Puedes ejecutar la aplicación desde la terminal usando el Maven Wrapper incluido:

```bash
# En Windows (PowerShell/CMD)
.\mvnw.cmd spring-boot:run

# En Linux/macOS
./mvnw spring-boot:run
```

O compilar el archivo JAR y ejecutarlo:

```bash
# En Windows (PowerShell/CMD)
.\mvnw.cmd clean package
java -jar target/prestamos-0.0.1-SNAPSHOT.jar
```

La API estará disponible en `http://localhost:8080`.

## 🔒 Seguridad y Roles

La API utiliza **JWT (JSON Web Token)** para la autenticación y **CORS** está habilitado para `http://localhost:5173` (Vite).

### Autenticación
Para obtener un token, utiliza el endpoint de login:
- `POST /api/auth/login`: Envía `username` y `password` para recibir un `token`.

### Uso del Token
Incluye el token en la cabecera de tus peticiones:
`Authorization: Bearer <tu_token>`

Se definen dos roles principales:

-   `ROLE_USER`: Puede solicitar préstamos y ver sus propios préstamos.
-   `ROLE_ADMIN`: Puede crear usuarios, ver todos los préstamos y **aprobar o rechazar** solicitudes.

**Configuración por defecto en `SecurityConfig`**:
- `POST /api/auth/login`: Público.
- `POST /api/users/**`: Solo ADMIN.
- `POST /api/loans/approve/**`: Solo ADMIN.
- `POST /api/loans/reject/**`: Solo ADMIN.

## 📡 Endpoints Principales

### Autenticación (`/api/auth`)
- `POST /api/auth/login`: Autenticar usuario y obtener token JWT.

### Usuarios (`/api/users`)
- `POST /api/users`: Crear un nuevo usuario (Requiere ADMIN).
- `GET /api/users`: Listar todos los usuarios (Requiere ADMIN).
- `GET /api/users/{id}`: Obtener detalle de un usuario.

### Préstamos (`/api/loans`)
- `POST /api/loans/request`: Solicitar un nuevo préstamo.
- `GET /api/loans`: Listar todos los préstamos (ADMIN ve todos, USER ve los suyos).
- `GET /api/loans/user/{userId}`: Ver préstamos de un usuario específico.
- `POST /api/loans/approve/{id}`: Aprobar un préstamo (Solo ADMIN).
- `POST /api/loans/reject/{id}`: Rechazar un préstamo (Solo ADMIN).

## 🧪 Pruebas

Para ejecutar las pruebas unitarias:

```bash
# En Windows
.\mvnw.cmd test

# En Linux/macOS
./mvnw test
```

Se han implementado pruebas críticas para la lógica de negocio de préstamos en `LoanServiceTest`.
