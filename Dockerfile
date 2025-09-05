FROM openjdk:21-jdk-slim AS build

WORKDIR /app

COPY gradlew ./gradlew
COPY gradle ./gradle
COPY build.gradle settings.gradle ./
COPY src ./src

RUN chmod +x gradlew

# 빌드에 필요한 패키지 설치 (xargs, unzip 등)
RUN apt-get update && apt-get install -y findutils unzip && rm -rf /var/lib/apt/lists/*

# Gradle 의존성 캐시
RUN ./gradlew dependencies --no-daemon || true

COPY . .

RUN ./gradlew build -x test --no-daemon

# ---- 실행 단계 ----
FROM eclipse-temurin:21-jre-alpine


WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
