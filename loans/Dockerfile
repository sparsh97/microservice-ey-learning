# Use official Maven image to build the app
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy pom.xml and download dependencies (for caching)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests


FROM eclipse-temurin:21-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Install curl, grep, and wget
RUN apk --no-cache add curl grep wget

# Copy the Spring Boot application JAR file into the container
COPY --from=builder /app/target/*.jar app.jar

RUN mkdir -p /app/libs && \
    wget -O /app/libs/opentelemetry-javaagent-2.11.0.jar https://repo1.maven.org/maven2/io/opentelemetry/javaagent/opentelemetry-javaagent/2.11.0/opentelemetry-javaagent-2.11.0.jar
# Expose the application port (change this based on your Spring Boot config)
EXPOSE 8090

# Run the application
ENTRYPOINT ["java", "-javaagent:/app/libs/opentelemetry-javaagent-2.11.0.jar", "-jar", "app.jar"]