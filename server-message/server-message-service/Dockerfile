FROM azul/zulu-openjdk-alpine:21.0.2
WORKDIR /app
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
