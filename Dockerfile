FROM maven:3.8.6-jdk-11
WORKDIR /app
COPY . .
RUN mvn clean install
ENTRYPOINT java -jar ./target/store-api-1.0.jar