FROM openjdk:8-jre
VOLUME /tmp
ADD SSMSeedProject.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS="SPRING_PROFILES_ACTIVE=production"
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]