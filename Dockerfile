FROM eclipse-temurin:17

LABEL maintainer="jackmu@umich.edu"

WORKDIR /app

COPY target/slowcapsules-0.0.1-SNAPSHOT.jar /app/sc-rest.jar

ENTRYPOINT ["java", "-jar", "sc-rest.jar"]