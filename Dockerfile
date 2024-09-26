FROM amazoncorretto:21-alpine-jdk
LABEL authors="Rj"
COPY target/DbDemo.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]