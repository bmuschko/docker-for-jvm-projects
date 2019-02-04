FROM openjdk:jre-alpine
LABEL maintainer="Benjamin Muschko"
COPY /build/libs/todo-web-service-exercise-1.0.0.jar /app/todo-web-service.jar
ENTRYPOINT ["java", "-jar", "/app/todo-web-service.jar"]
EXPOSE 8080