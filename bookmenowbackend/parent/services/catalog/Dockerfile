# Use official OpenJDK 17 image as the base image
FROM eclipse-temurin:17-alpine

# Create a new group and user
RUN addgroup -g 1028 devopsc \
    && adduser -D -G devopsc admin

# Mount point
VOLUME /tmp

# ARG para especificar el JAR desde la terminal
ARG JAR_FILE

# Copiamos el JAR construido (fat jar de Spring Boot)
COPY ${JAR_FILE} app.jar

# Cambio de permisos
RUN chown -R admin:devopsc /tmp

# Cambiamos al usuario no root
USER admin

# Corrección: usar shell form para que JAVA_OPTS funcione
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
