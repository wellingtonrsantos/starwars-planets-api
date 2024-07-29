FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/starwars-planets-0.0.1-SNAPSHOT.jar /app/starwars-planets-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "starwars-planets-0.0.1-SNAPSHOT.jar"]
