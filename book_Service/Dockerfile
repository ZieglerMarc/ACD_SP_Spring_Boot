# Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /opt/app

# Copy the .jar file into the container
COPY bookService-v1.0.0.jar /opt/app

# Expose the port that your application listens on (z. B. 8888)
EXPOSE 8080

# Run the .jar file
ENTRYPOINT ["java", "-jar", "/opt/app/bookService-v1.0.0.jar"]