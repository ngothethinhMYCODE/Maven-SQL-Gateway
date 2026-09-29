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

# Copy our WAR into ROOT
COPY --from=build /app/target/Maven-SQL-Gateway.war \
    /usr/local/tomcat/webapps/ROOT.war

# Render default web port
EXPOSE 10000

# Make Tomcat listen on Render's PORT
CMD ["sh", "-c", "sed -i 's/port=\"8080\"/port=\"${PORT:-10000}\"/' /usr/local/tomcat/conf/server.xml && catalina.sh run"]