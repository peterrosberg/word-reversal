FROM openjdk:14-jdk-alpine

EXPOSE 8080

COPY backend/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]