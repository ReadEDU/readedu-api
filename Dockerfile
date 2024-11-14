# Usar una imagen base ligera de OpenJDK 21 para ejecutar aplicaciones Java
FROM openjdk:21-jdk-slim

# Define la variable del archivo JAR
ARG JAR_FILE=target/readedu-api-0.0.1.jar

# Copia el archivo JAR en el contenedor
COPY ${JAR_FILE} readedu-api.jar

# Expone el puerto 8080
EXPOSE 8080

# Comando para ejecutar el archivo JAR
ENTRYPOINT ["java", "-jar", "readedu-api.jar"]