# Start with a base image containing Java runtime
FROM eclipse-temurin:17-jre-alpine

# Set the working directory inside the container
WORKDIR /opt/app

# Copy the .jar file into the container
COPY target/bookService-v1.0.0.jar /opt/app

# Expose the port that your application listens on
EXPOSE 8080

# Run the .jar file
ENTRYPOINT ["java", "-jar", "/opt/app/bookService-v1.0.0.jar"]