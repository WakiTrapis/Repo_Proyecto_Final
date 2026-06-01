# iR InkReserve

Aplicación web de gestión para estudios de tatuajes desarrollada con Spring Boot.
Permite gestionar artistas, clientes, citas y diseños de tatuajes desde un panel de administración.

---

## Tecnologías utilizadas

- **Java 21**
- **Spring Boot 4.0.6**
  - Spring MVC
  - Spring Security
  - Spring Data JPA
- **Thymeleaf** — motor de plantillas
- **H2** — base de datos en memoria
- **Bootstrap 5.3.2** — interfaz de usuario
- **Bootstrap Icons 1.11.1**
- **Lombok**
- **Maven**

---

## Acceso a la aplicación

| Usuario | Contraseña | Rol |
|---|---|---|
| admin | admin | ADMIN |
| user | user | USER |
| (nombre del artista) | (DNI del artista) | USER |

---

## Flujo de la aplicación

### ADMIN
- Accede al **Dashboard principal** con estadísticas y acciones rápidas
- Gestión completa de **artistas** (crear, editar, eliminar)
- Gestión completa de **clientes**, **citas** y **tatuajes**
- Al crear un artista se genera automáticamente su usuario con rol USER

### USER (Artista)
- Accede directamente al **listado de artistas**
- Puede consultar, crear y editar **clientes**, **citas** y **tatuajes**
- No puede crear, editar ni eliminar artistas
- No tiene acceso al Dashboard principal

---

## Seguridad

- Login en `/login`, logout en `/logout`
- Redirección automática según rol al iniciar sesión
- Página de acceso denegado en `/acceso-denegado`
- Cambio de contraseña en `/cambiar-contrasena`

