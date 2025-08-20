# --- Stage 1: Build ---
# Use a full JDK image to build the project.
# The 'AS builder' tag allows us to refer to this stage later.
FROM eclipse-temurin:21-jdk-jammy AS builder

# Set the working directory inside the container.
WORKDIR /app

# Copy the pom.xml first to leverage Docker's layer caching.
# If pom.xml doesn't change, dependencies won't be downloaded again.
COPY pom.xml .

# Download all project dependencies.
RUN mvn dependency:go-offline -B

# Copy the rest of the project's source code.
COPY src ./src

# Compile the project and generate the .jar file, skipping tests.
# The result will be in /app/target/
RUN mvn clean install -DskipTests

# --- Stage 2: Runtime ---
# Use a JRE-only image, which is smaller and more secure for production.
FROM eclipse-temurin:21-jre-jammy

# Set the working directory.
WORKDIR /app

# Copy the .jar file generated in the build stage to the final image.
# The artifact name was taken from your pom.xml.
# We rename it to 'app.jar' to simplify the execution command.
COPY --from=builder /app/target/dslist-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080, which is the default for Spring Boot.
EXPOSE 8080

# Command to run the application when the container starts.
ENTRYPOINT ["java", "-jar", "app.jar"]
