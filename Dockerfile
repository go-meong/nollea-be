FROM gradle:8.14.2-jdk21 AS builder

ENV GRADLE_USER_HOME=/tmp/.gradle
ENV KOTLIN_COMPILER_HOME=/tmp/.kotlin
ENV JAVA_TOOL_OPTIONS="-XX:-UsePerfData"
ENV KOTLIN_DAEMON_ENABLED=false

WORKDIR /app
COPY . .

RUN chmod +x ./gradlew
RUN ./gradlew clean bootJar --no-daemon --warning-mode all --no-parallel

FROM eclipse-temurin:21-jre

WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
