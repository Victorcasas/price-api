FROM maven:eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests
ARG JAR_FILE=target/*.jar
COPY --from=build /app/target/priceapi.jar /app.jar
EXPOSE 8080
CMD ["java","-jar","/app.jar"]
