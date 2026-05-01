FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY .. .

RUN chmod +x gradlew

RUN ./gradlew :app:build

CMD ["./gradlew", ":app:run"]