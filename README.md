Aquí tienes una propuesta de archivo `README.md` profesional y completa para tu repositorio, incluyendo la instrucción específica sobre la configuración del secreto de JWT y las tecnologías que has implementado (Arquitectura Hexagonal, Spring Boot, React, etc.).

---

# Technical Assessment - Sistema de Gestión de Proyectos y Tareas

Este proyecto es una aplicación full-stack diseñada para la gestión de proyectos y sus tareas asociadas. Está construido siguiendo los principios de la **Arquitectura Hexagonal (Puertos y Adaptadores)** para garantizar un código mantenible, testeable y desacoplado.

## 🚀 Tecnologías Utilizadas

### Backend

* **Java 21** & **Spring Boot 3.4.1**
* **Spring Security** con **JWT** (JSON Web Tokens)
* **Spring Data JPA** con **MySQL**
* **Springdoc OpenAPI (Swagger UI)** para documentación de la API
* **Arquitectura Hexagonal** (Domain, Application, Infrastructure)

### Frontend

* **React** (Vite)
* **Tailwind CSS** para los estilos
* **Axios** para consumo de APIs

---

## 🛠️ Configuración y Requisitos Previos

### 1. Base de Datos

El sistema utiliza MySQL. Asegúrate de tener una instancia corriendo (local o Docker) con las siguientes credenciales por defecto:

* **URL:** `jdbc:mysql://localhost:3307/technical_db`
* **Usuario:** `root`
* **Password:** `root`

### 2. Importante: Configuración del Secreto JWT

Para que la aplicación inicie correctamente, es **obligatorio** configurar la clave secreta de firmado para los tokens.

En el archivo `src/main/resources/application.properties` (o el archivo de configuración correspondiente), asegúrate de tener la siguiente línea:

```properties
jwt.secret=${JWT_SECRET:4dbbc06579308e56999a77054f59052b610c558c7310464654876241}

```

> **Nota:** Esta configuración permite que Spring busque una variable de entorno llamada `JWT_SECRET`. Si no la encuentra, utilizará el valor por defecto proporcionado después de los dos puntos.

---

## 🏗️ Ejecución del Proyecto

### Backend

1. Navega a la carpeta del backend: `cd Technical-assessment`
2. Ejecuta el comando: `./mvnw spring-boot:run`
3. La API estará disponible en: `http://localhost:8080`

### Frontend

1. Navega a la carpeta del frontend: `cd project-management-front`
2. Instala dependencias: `npm install`
3. Inicia el servidor de desarrollo: `npm run dev`
4. Accede a: `http://localhost:5173` (o el puerto que indique la consola)

---

## 📖 Documentación de la API (Swagger)

Una vez que el backend esté en ejecución, puedes consultar y probar todos los endpoints (Auth, Projects, Tasks) desde la interfaz de Swagger:

🔗 **URL:** `http://localhost:8080/swagger-ui/index.html`

**Pasos para probar:**

1. Registrarse en `/api/auth/register`.
2. Iniciar sesión en `/api/auth/login` para obtener el Token.
3. Copiar el token y usar el botón **Authorize** de Swagger con el formato: `Bearer <tu_token>`.

---

## 📁 Estructura del Proyecto (Arquitectura Hexagonal)

* **Domain:** Contiene los modelos de negocio y las interfaces de los repositorios.
* **Application:** Contiene los casos de uso (`Use Cases`) y los puertos de entrada/salida.
* **Infrastructure:** Contiene las implementaciones técnicas (Adaptadores de persistencia, controladores REST, configuración de seguridad, etc.).

---

## 📄 Licencia

Este proyecto se entrega como parte de una evaluación técnica. Todos los derechos reservados.