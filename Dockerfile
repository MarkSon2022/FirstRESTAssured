# Start with a Maven image that includes JDK 21
FROM maven:3.9.9-eclipse-temurin-21

# Install Git and update Maven (if needed)
RUN apt-get update && \
    apt-get install -y git && \
    apt-get clean

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files to the container
COPY pom.xml .
COPY src ./src

# Verify Maven and Git versions
RUN mvn -version && git --version

# Download all dependencies needed for both compilation and testing
RUN mvn dependency:go-offline

# Specify the command to run tests using Maven
CMD ["mvn", "test"]
