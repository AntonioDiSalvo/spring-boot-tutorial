FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/spring-boot-tutorial-0.0.6-SNAPSHOT.jar /app/spring-boot-tutorial-0.0.6-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "spring-boot-tutorial-0.0.6-SNAPSHOT.jar"]
