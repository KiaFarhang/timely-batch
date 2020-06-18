FROM openjdk:9
RUN addgroup --system timely && adduser --system timely-batch --ingroup timely
USER timely-batch:timely
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]