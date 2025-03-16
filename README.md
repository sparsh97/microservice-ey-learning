# Microservice with SpringBoot, Docker, and K8s

Learning microservice using spring boot, docker, and k8s.

## Requirements

- [JDK 21](https://adoptium.net/temurin/releases/?os=windows)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method of the class from your IDE.

Alternatively, you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update the tests as appropriate.

## For Local Testing
RabbitMQ
````
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4.0-management
````
using postgress docker image
````
datasource:
    url: jdbc:postgresql://localhost:5432/accounts
    username: postgress
    password: postgress
    driver-class-name: org.postgresql.Driver
  jpa:
    database-platform: org.hibernate.dialect.PostgreSQLDialect
````      
using h2 db for without docker
````
datasource:
    url: jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
    username: sa
    password:
    driver-class-name: org.h2.Driver
jpa:
    database-platform: org.hibernate.dialect.H2Dialect

````
