FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN chmod +x mvnw
COPY src/ src/

RUN ./mvnw package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=build /app/target/quarkus-app/ ./quarkus-app/

CMD ["java", "-jar", "/app/quarkus-app/quarkus-run.jar"]
