FROM gradle:8.2-jdk17-alpine

WORKDIR /home/mwo

COPY build.gradle .

COPY settings.gradle .

COPY src/ src/

RUN gradle build -x test

RUN mv build/libs/mwo-backend-0.0.1-SNAPSHOT.jar mwo.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "mwo.jar"]

