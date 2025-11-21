FROM maven:4.0.0-rc-4-eclipse-temurin-21 AS build

WORKDIR /build

COPY anime-service/src anime-service/src
COPY anime-service/pom.xml anime-service/pom.xml
COPY gateway-service/src gateway-service/src
COPY gateway-service/pom.xml gateway-service/pom.xml
COPY user-service/src user-service/src
COPY user-service/pom.xml user-service/pom.xml
COPY rating-service/src rating-service/src
COPY rating-service/pom.xml rating-service/pom.xml
COPY pom.xml pom.xml

RUN mvn clean install -DskipTests
RUN mvn -pl rating-service clean package -DskipTests


FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /build/rating-service/target/*.jar rating-service.jar

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "rating-service.jar"]