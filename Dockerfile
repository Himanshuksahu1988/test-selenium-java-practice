FROM maven:3.9.11-eclipse-temurin-17

WORKDIR /app

COPY . .

RUN mvn clean test-compile

CMD ["mvn","test"]