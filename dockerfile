FROM openjdk:17 AS builder
WORKDIR /app

COPY mvnw mvnw
COPY .mvn .mvn
COPY pom.xml pom.xml
RUN chmod 777 mvnw
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package

FROM openjdk:17
WORKDIR /app
COPY --from=builder /app/target/launch-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8881
ENTRYPOINT ["java","-jar","app.jar"]