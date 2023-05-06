FROM --platform=linux/x86_64 eclipse-temurin:17-jdk-jammy AS build

COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
COPY src src

RUN --mount=type=cache,target=/root/.m2,rw ./mvnw package -DskipTests

FROM --platform=linux/x86_64 eclipse-temurin:17-jre-jammy

ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v1.31.0/opentelemetry-javaagent.jar opentelemetry-javaagent.jar

COPY --from=build target/thingies-1.0.0.jar thingies.jar

EXPOSE 8080
ENTRYPOINT ["java", "-javaagent:opentelemetry-javaagent.jar", "-jar", "thingies.jar"]
