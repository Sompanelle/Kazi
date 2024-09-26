FROM amazoncorretto:21-alpine-jdk
LABEL authors="Rj"
COPY target/DbDemo.jar app.jar
CMD apt-get update -y
ENTRYPOINT ["java", "-jar", "/app.jar"]