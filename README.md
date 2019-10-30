# Commerce ETL

This architectural component represents the ETL process to fetch commerce data from Carso Catalogs, tranforms this data to the commerce structure and load into "Commerce" project database. In some catalogs the location data of commerce (coordinates) isn't available, therefore it was mandatory the integration of Google Geocoding service to complete the missing data.

## 1. Requirements

The next tools are needs to run this project, install all tools according with the O.S.:

 - [Apache Maven 3.6.2](https://maven.apache.org/)
 - [Java 1.8.*](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
 - IDE Text Editor
 - [Google Maps API Key](https://developers.google.com/maps/documentation/javascript/get-api-key)
 - [Docker >=18.03.0-ce](https://runnable.com/docker/getting-started/)

## 2. Installation

#### 2.1 Environtment set up

If you have a UNIX environment, you can run the next commands over terminal:

```bash
  # Go into enviroment settings folder.
  cd src/main/resources/env

  # Set development environment
  cp config.example.properties config.development.properties

  # Set production environment
  cp config.example.properties config.production.properties
```

At end, set the environment properties replacing the values with custom credentials and Google Maps API key.

#### 2.2 Project performance

There are many ways to run this project. If you complete the requirements, then over the main path you run the next commands to reach this goal:

```bash
mvn clean

mvn package -P <environment>

mvn exec:java -Dexec.cleanupDaemonThreads=false
```

There are two options to environment tag: development or production, according with one or other run the project with environment configuration.

#### 2.3 Build docker image

You must follow the next steps to build and run an container with the ETL java application:

```bash
#Build application
mvn package -P <environment>

#Build image
docker build . -t commerce--etl:<environment>

#Run image. If you using internal containers, set --link parameters in other
# case drop "--link" lines.
docker run \
  -it --rm \
  --name commerce--etl \
  --link <claro pay DB container>:commerce--db-claropay \
  --link <commerce DB souce container>:commerce--db-etl-source \
  commerce--etl:<environment>
```

## 3. Other links and tutorials

Links to Goecoding API:

 - [Google Maps Java Client Library](https://github.com/googlemaps/google-maps-services-java)
 - [Geocoding Documentation](https://developers.google.com/maps/documentation/geocoding/intro)
 - [Google Maps API prices](https://cloud.google.com/maps-platform/pricing/sheet/?hl=es-419)


Maven project:

 - [Maven profiles](https://www.mkyong.com/maven/maven-profiles-example/)

Docker images to java project:

 - [Crafting the perfect Java Docker build flow](https://codefresh.io/docker-tutorial/java_docker_pipeline/)
 
## 4. Developers team.

 Software Developer: Ricardo Bermúdez Bermúdez
 - E-mail:       rbermudez@ndscognitivelabs.com
 - Mobile phone: 5548879549    
 - Job title:    Full Stack Software Engineer
