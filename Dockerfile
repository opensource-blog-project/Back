FROM gradle:8.11.1-jdk21 AS builder

# 작업 디렉토리 설정
WORKDIR /build

# 소스 복사
COPY . .

# Gradle Wrapper 실행 권한 부여
RUN chmod +x ./gradlew

# Gradle 빌드 (테스트 제외)
RUN ./gradlew clean build -x test

# -plain.jar을 제외한 JAR 파일 선택 및 복사
RUN jar_file=$(ls build/libs/*.jar | grep -v "plain.jar" | head -n 1) && \
    echo "선택된 JAR 파일: $jar_file" && \
    cp "$jar_file" application.jar

# 실행 단계
FROM openjdk:21-slim

# 작업 디렉토리 설정
WORKDIR /app

# 빌드된 JAR 파일 복사
COPY --from=builder /build/application.jar app.jar

# 컨테이너 시작 명령어
ENTRYPOINT ["java", "-jar", "app.jar"]
