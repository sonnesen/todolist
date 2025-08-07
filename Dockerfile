FROM maven:3.9.9-eclipse-temurin-21-alpine AS maven-cache
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B \
    && mvn dependency:resolve-sources -B \
    && mvn dependency:resolve -B

FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY --from=maven-cache /root/.m2 /root/.m2
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests -o

FROM eclipse-temurin:21-jre-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY --from=build /app/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]