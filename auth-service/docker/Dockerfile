FROM amazoncorretto:21
LABEL maintainer="cookease-team"
WORKDIR /app
COPY auth-service-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]