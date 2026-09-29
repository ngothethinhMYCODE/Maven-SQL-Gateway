# =========================
# Stage 1: Build WAR
# =========================
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests


# =========================
# Stage 2: Run Tomcat
# =========================
FROM tomcat:11.0-jdk21-temurin

# Remove default Tomcat applications
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy our WAR into Tomcat
COPY --from=build /app/target/Maven-SQL-Gateway.war \
    /usr/local/tomcat/webapps/ROOT.war

# Render uses PORT environment variable.
# Tomcat listens on 8080 inside the container.
EXPOSE 8080

CMD ["catalina.sh", "run"]