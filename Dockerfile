# Base Alpine Linux based image with OpenJDK JRE only
FROM openjdk:8-jre-alpine

# copy application WAR (with libraries inside)
COPY target/libs /libs
COPY target/cercademi_etl-1.0.0.jar /app.jar

# specify default command
CMD ["/usr/bin/java", "-jar", "/app.jar"]