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

# Use a lightweight JDK image for running the application
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Install curl, grep, and wget
RUN apk --no-cache add curl grep wget

# Copy the built JAR from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the application port
EXPOSE 9001

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
