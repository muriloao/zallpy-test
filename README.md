# ZallpyTest
A simple implementation of the Zallpy test
https://bitbucket.org/zallpylabs/java-react-challenge/src/master/


## Tecnologies
- Eureka Netflix Server/Client
- Spring Boot microservices
- Zuul Gateway
- MySQL
- ReactJS
- Docker
- Docker-compose
- React + Redux

How to run this example :

```sh
## build docker images
mvn clean install

##should display three freshly built docker images
docker images

##start up all instances
docker-compose up
```

Acess Frontend app
http://localhost:3000

Access Eureka Server
http://localhost:8761

Access Zuul Gateway
http://localhost:8080/routes

Access Swagger Documentation
http://localhost:8080/swagger-ui.html

