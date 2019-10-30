FROM openjdk:8-jre-alpine

# Copy application JAR (with libraries inside)
COPY target/cercademi_etl-1.0.0-jar-with-dependencies.jar /app.jar

# Run app
CMD ["/usr/bin/java", "-jar", "/app.jar"]