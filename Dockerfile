# Use an official OpenJDK image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the jar file built by Maven
COPY target/examverse-backend-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 (Render uses this)
EXPOSE 8080

# Start the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]