# Utiliser une image de base avec maven et jdk 11 pour construire l'application
FROM maven:3.8.1-openjdk-11 as build

# Copier le pom.xml et les sources
COPY pom.xml /usr/src/gatewayservice/
COPY src /usr/src/gatewayservice/src

# Construire l'application
WORKDIR /usr/src/gatewayservice
RUN mvn package

# Utiliser une image de base avec jdk 11 pour exécuter l'application
FROM openjdk:11-jre-slim

# Copier l'application construite dans le conteneur
COPY --from=build /usr/src/gatewayservice/target/Gateway-0.0.1-SNAPSHOT.jar /Gateway.jar

# Définir le point d'entrée pour lancer l'application
ENTRYPOINT ["java", "-jar", "/Gateway.jar"]
