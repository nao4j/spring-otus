FROM maven:3.6.0-jdk-11 AS build

ENV PROJECT_DIR="/opt/otus_library"
RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR

ADD ./pom.xml $PROJECT_DIR
ADD ./src $PROJECT_DIR/src

RUN mvn clean package



FROM azul/zulu-openjdk:11.0.1 AS runtime

ENV PROJECT_DIR="/opt/otus_library"
RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR
COPY --from=0 $PROJECT_DIR/target/m4-h1-1.0-SNAPSHOT.jar $PROJECT_DIR/

EXPOSE 8080

CMD ["java", "-jar", "/opt/otus_library/m4-h1-1.0-SNAPSHOT.jar"]
