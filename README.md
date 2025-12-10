# Auth API con JWT - Spring Boot

API REST de autenticación desarrollada en **Java + Spring Boot**, que permite registro de usuarios, login con contraseñas encriptadas, generación de tokens JWT y protección de endpoints.

## Tecnologías
- Java 21
- Spring Boot
- Spring Security
- JWT (jjwt)
- BCrypt
- Spring Data JPA
- MySQL
- Lombok

## Funcionalidades
- Registro de usuarios
- Login con validación de credenciales
- Encriptación de contraseñas
- Generación de token JWT
- Validación de token en endpoints protegidos
- Roles (ADMIN, USER)
- API REST probada con Postman

## Endpoints
- POST `/api/auth/register`
- POST `/api/auth/login`
- GET `/api/test/protegido` (requiere JWT)

##  Cómo ejecutar el proyecto
1. Crear base de datos en MySQL:
   ```sql
   CREATE DATABASE auth_jwt_db;
