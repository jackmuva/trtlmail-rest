FROM eclipse-temurin:17

LABEL maintainer="jackmu@umich.edu"

WORKDIR /app

COPY target/trtmail-rest.jar /app/trtlmail-rest.jar

ENTRYPOINT ["java", "-jar", "sc-rest.jar"]