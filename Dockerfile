# Stage de compilación Maven
FROM maven:3.8.4-openjdk-17-slim AS build

COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Copiar el JAR justo después de compilar
RUN cp target/*.jar /opt/priceapi.jar 

# Stage de ejecución 
FROM eclipse-temurin:17-jre

# Copiar el JAR generado en el stage de Maven
COPY --from=build /opt/priceapi.jar .

EXPOSE 8080

# Ejecutar el JAR
CMD ["java", "-jar", "priceapi.jar"]
