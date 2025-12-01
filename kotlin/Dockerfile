FROM maven:latest as maven
COPY pom.xml .
COPY src ./src
RUN mvn clean
RUN mvn package

FROM openjdk:latest
COPY --from=maven /target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]