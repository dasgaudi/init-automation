FROM adoptopenjdk/openjdk11:alpine-slim
VOLUME /tmp
ARG DEPENDENCY=/infrastructure/build/unpacked
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
EXPOSE 9080
ENTRYPOINT [ "sh", "-c", "source /vault/secrets/config && java -cp app:app/lib/* ag.bushel.karibou.KaribouApplicationKt" ]
