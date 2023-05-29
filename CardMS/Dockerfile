# Utiliser une image de base avec maven et jdk 11 pour construire l'application
FROM maven:3.8.1-openjdk-11 as build

# Copier le pom.xml et les sources
COPY pom.xml /usr/src/cardservice/
COPY src /usr/src/cardservice/src

# Construire l'application
WORKDIR /usr/src/cardservice
RUN mvn package

# Utiliser une image de base avec jdk 11 pour exécuter l'application
FROM openjdk:11-jre-slim

# Copier l'application construite dans le conteneur
COPY --from=build /usr/src/cardservice/target/CardApplication-0.0.1-SNAPSHOT.jar /CardApplication.jar

# Définir le point d'entrée pour lancer l'application
ENTRYPOINT ["java", "-jar", "/CardApplication.jar"]
