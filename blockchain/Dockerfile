FROM azul/zulu-openjdk-alpine:11
COPY ./build/libs/backend-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]