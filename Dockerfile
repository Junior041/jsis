FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copy Maven wrapper and pom.xml first to leverage Docker cache
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

# Copy the rest of the application code
COPY src ./src

# Expose the port the application will run on
EXPOSE 8080

# Run the application
CMD ["./mvnw", "spring-boot:run"]
