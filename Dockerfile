FROM openjdk:21-slim

# 작업 디렉토리 설정
WORKDIR /app


# 소스 복사
COPY . .

# Gradle Wrapper 실행 권한 부여 및 빌드
RUN chmod +x ./gradlew
RUN ./gradlew clean build -x test && ls build/libs

# 빌드된 JAR 파일 복사
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# 컨테이너 시작 명령어
ENTRYPOINT ["java", "-jar", "app.jar"]

