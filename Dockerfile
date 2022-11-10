
FROM maven:3.8.6 as build
ENV HOME=/home/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD pom.xml $HOME
RUN mvn verify --fail-never
ADD . $HOME
RUN mvn package

FROM openjdk:18
COPY --from=build /home/app/target/*.jar /app/runner.jar
ENTRYPOINT java -jar /app/runner.jar