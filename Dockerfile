FROM openjdk:18
WORKDIR /app
COPY ./target/CarPool1-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "CarPool1-0.0.1-SNAPSHOT.jar"]
