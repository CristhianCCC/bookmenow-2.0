# Use official OpenJDK 17 image as the base image
FROM eclipse-temurin:17-alpine

# Create a new group and user
RUN addgroup -g 1028 devopsc \
    && adduser -D -G devopsc admin

# Define mount point for temporary files
VOLUME /tmp

# ARG to specify the JAR from the terminal
ARG JAR_FILE

# Copy the built JAR file (Spring Boot fat JAR)
COPY ${JAR_FILE} app.jar

# Change ownership permissions
RUN chown -R admin:devopsc /tmp

# Switch to non-root user
USER admin

# Use shell form so JAVA_OPTS environment variable works
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
