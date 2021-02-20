FROM java:8-jdk-alpine

COPY ./target/person-1.0-exe.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch person-1.0-exe.jar'

ENTRYPOINT ["java","-jar","person-1.0-exe.jar"]
