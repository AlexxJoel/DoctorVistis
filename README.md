# DoctorVistis Sistema de registor de citas médicas

Este proyecto permite registrar y gestionar consultas médicas en una clínica u hospital, facilitando el seguimiento de médicos, pacientes y el historial clínico a través de una interfaz amigable y una API documentada.

✅ Funcionalidades
* Registro de médicos
* Registro de pacientes
* Agenda de citas médicas
* Atención de citas
* Consulta de historial médico


  🧱 Arquitectura del Proyecto
`````scss
📦 backend
 ┣ 📂controller (API REST)
    ┣ 📂dto (clases de transferencia de datos)
    ┣ 📂 docs
 ┣ 📂model (clases de dominio con entidades, repositorios y servicios)
    ┣ 📂entities
    ┣ 📂repositories
    ┣ 📂services 
 ┣ 📂core (clases base y utilidades)
 ┗ 📂config (configuración general, Swagger, etc.)

`````


## Tecnologías Usadas
🔹 Backend
Java 8

Spring Boot

MyBatis (con anotaciones para consultas SQL)

Oracle Database

Swagger para documentación de la API
→ Accesible en: http://localhost:8080/api/v1/doctor-visits/swagger-ui/index.html

🔸 Frontend
Angular 19

Bootstrap 5 (para el diseño de componentes visuales)
