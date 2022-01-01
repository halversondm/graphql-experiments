FROM openjdk:17-alpine
COPY target/graphql-experiments-0.0.1-SNAPSHOT.jar /usr/app/graphql-experiments.jar
WORKDIR /usr/app
EXPOSE 8080
CMD ["java", "-jar", "graphql-experiments.jar"]