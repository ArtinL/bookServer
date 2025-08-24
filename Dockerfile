# ---- Build stage
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app


COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN chmod +x mvnw && sed -i 's/\r$//' mvnw

RUN ./mvnw -q -DskipTests dependency:go-offline

COPY src ./src
RUN ./mvnw -q -DskipTests clean package

# ---- Runtime stage
FROM eclipse-temurin:17-jre
WORKDIR /app
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75.0"
ENV SERVER_PORT=8080
EXPOSE 8080
COPY --from=build /app/target/*SNAPSHOT.jar app.jar
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -Dserver.port=${PORT:-$SERVER_PORT} -jar /app/app.jar"]
