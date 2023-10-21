# Estágio 1: Construir o JAR da aplicação
FROM maven:3.8.3-jdk-17 AS build
COPY . /app
WORKDIR /app
RUN mvn clean package

# Estágio 2: Empacotar o JAR em uma imagem Docker
FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build /app/target/pizzaria-0.0.1.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
