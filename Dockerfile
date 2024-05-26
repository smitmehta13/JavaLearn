# Use the Maven image to build the project
FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app

# Copy the Maven wrapper scripts and properties file
COPY .mvn/ .mvn/
COPY mvnw .
COPY pom.xml .

# Ensure the Maven wrapper properties file exists
RUN test -f .mvn/wrapper/maven-wrapper.properties

# Download dependencies and go offline
RUN ./mvnw dependency:go-offline

# Copy the project source
COPY src ./src

# Build the project
RUN ./mvnw clean package

# Use the OpenJDK image for the runtime
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Run the application
CMD ["java", "-jar", "app.jar"]
