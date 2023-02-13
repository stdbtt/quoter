FROM maven:3.8.7-openjdk-18

WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

CMD mvn spring-boot:run