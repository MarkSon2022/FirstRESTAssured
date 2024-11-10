# Start with a Maven image that includes JDK 21
FROM maven:3.9.9-eclipse-temurin-21

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files to the container
COPY pom.xml .
COPY src ./src
RUN mvn -version

# Download Maven dependencies (this step allows Docker to cache dependencies)
RUN mvn dependency:resolve

# Specify the command to run tests using Maven
CMD ["mvn", "test"]
