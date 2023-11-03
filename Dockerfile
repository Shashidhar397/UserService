# Use an official OpenJDK runtime as a parent image
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file into the container at the specified location
COPY target/userService-1.0.jar userService.jar

# Specify the command to run your Spring Boot application
CMD ["java", "-jar", "userService.jar"]
