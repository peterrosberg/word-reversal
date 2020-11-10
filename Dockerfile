FROM openjdk:14-jdk-alpine

EXPOSE $PORT

COPY backend/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]