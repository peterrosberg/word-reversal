FROM openjdk:14-jdk-alpine

EXPOSE $PORT

COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]