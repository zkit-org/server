FROM azul/zulu-openjdk-alpine:17-latest
WORKDIR /app
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
