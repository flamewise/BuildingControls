# Use the official Maven image with OpenJDK 17
FROM maven:3.9-amazoncorretto-17 AS build

# Set working directory
WORKDIR /app

# Copy project files to the container
COPY . /app

# Build the application using Maven
RUN mvn clean package -DskipTests

# Use OpenJDK for runtime
FROM openjdk:17-jdk-slim

# Set working directory in the runtime container
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/BuildingControls-1.0-SNAPSHOT.jar /app/BuildingControls.jar

# Expose port (if needed)
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "/app/BuildingControls.jar"]
