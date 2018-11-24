FROM azul/zulu-openjdk:11.0.1

ENV PROJECT_DIR="/opt/otus_spring_integration"
RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR
ADD ./target/m3-h4-*.jar m3-h4.jar

EXPOSE 8080

CMD ["java", "-jar", "/opt/otus_spring_integration/m3-h4.jar"]
