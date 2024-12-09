FROM eclipse-temurin:21-jdk-alpine

WORKDIR /franquicia

COPY target/api-0.0.1-SNAPSHOT.jar api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "api.jar"]
