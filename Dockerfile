FROM arm64v8/eclipse-temurin
VOLUME /tmp
ARG JAR_FILE
COPY docker-run.sh .
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["./docker-run.sh"]
