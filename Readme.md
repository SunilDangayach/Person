# springboot-person-app

Minimal [Spring Boot](http://projects.spring.io/spring-boot/) person app.

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)


## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.fire.PersonApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Deploying the application using docker

- Create docker image using `docker build -t person-app .`
- Run the docker image using `docker run -p 8080:8080 person-app`


## API's

Currently this application support these operations.
- GET - Get all the person.
- PUT - Update a person.
- POST - Create a person.
- DELETE - Delete a person.


## API documentation.

[Swagger API URL](http://localhost:8080/swagger-ui.html#/person-controller)

## Postman API collection

Please see in the root directory of project folder - `Fire.postman_collection.json`
