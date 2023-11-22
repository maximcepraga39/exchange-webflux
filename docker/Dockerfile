FROM eclipse-temurin:11-jdk-alpine AS build

COPY ../exchange-app/build/libs/exchange-app-0.0.1-SNAPSHOT.jar app.jar
COPY ../exchange-app/build/resources/main/application.yml application.yml

RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)

FROM eclipse-temurin:11-jre-alpine

ARG DEPENDENCY=/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","md.orange.exchangeapp.ExchangeAppApplication"]