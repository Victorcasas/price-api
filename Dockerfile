# Stage de compilación Maven
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app 

COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage de ejecución 
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copiar el JAR generado en el stage de Maven
COPY --from=build /app/target/priceapi.jar .

# Ejecutar el JAR
CMD ["java", "-jar", "my-application.jar"]
