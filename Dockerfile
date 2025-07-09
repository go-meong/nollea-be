# 1단계: Builder stage
FROM gradle:8.14.2-jdk21 AS builder

# Gradle, Kotlin 관련 캐시 및 데몬 경로를 임시 디렉터리로 지정
ENV GRADLE_USER_HOME=/tmp/.gradle
ENV KOTLIN_COMPILER_HOME=/tmp/.kotlin

# JVM 성능 데이터 비활성화 옵션 추가 (hsperfdata 문제 해결용)
ENV JAVA_TOOL_OPTIONS="-XX:-UsePerfData"

# Kotlin 데몬 비활성화
ENV KOTLIN_DAEMON_ENABLED=false

WORKDIR /app
COPY . .

RUN chmod +x ./gradlew
RUN ls -la

RUN ./gradlew clean bootJar --no-daemon --warning-mode all

# 2단계: Runtime stage
FROM eclipse-temurin:21-jre

WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
