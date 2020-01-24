# Digital Hub - Backend Assignment

REST API according to provided requirements with the functionality of transferring money and showing the past transactions in a historical transaction list.

## Requirements
1. JDK 1.8+
2. Maven (latest version)

## How to run?
First unzip the project, then into project folder run this command in terminal:

`mvn spring-boot:run`

### Swagger documentation
To view API documentation, once project is running in localhost, open in browser this url:

http://localhost:8080/api/swagger-ui.html

## Approach followed
The project was build using maven and spring boot. Inital package was configurated and downloaded from https://start.spring.io/. This one contains all necesary to build RESTful services.

Into this source code, we write code related to functionality requested using Spring Framework: controllers, services, repositories (using interfaces and own implemetations).

In the other hand, pom.xml file includes dependencies related with Swagger Documentation of which provide Java annotations to generate info about RESTful methods into one web interface.