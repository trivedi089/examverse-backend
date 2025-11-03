# ---------- Stage 1: Build the JAR ----------
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy only pom.xml first to leverage Docker caching
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy all source files
COPY src ./src

# Package the app
RUN mvn clean package -DskipTests

# ---------- Stage 2: Run the JAR ----------
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy the built JAR from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080 (Render requires this)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]