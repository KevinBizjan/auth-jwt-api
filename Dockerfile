# 1. Imagen base con Java 21
FROM eclipse-temurin:21-jdk

# 2. Directorio de trabajo dentro del contenedor
WORKDIR /app

# 3. Copiamos todo el proyecto
COPY . .

# 4. Construimos el JAR
RUN ./mvnw clean package -DskipTests

# 5. Exponemos el puerto de Spring Boot
EXPOSE 8080

# 6. Ejecutamos la app
CMD ["java", "-jar", "target/auth-jwt-api-0.0.1-SNAPSHOT.jar"]
