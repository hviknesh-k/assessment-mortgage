# Spring Boot Assessment Project

## About
Assessment project to build 2 API endpoints using Spring Boot framwork

## Requirement Assumptions

* Interest rate and mortgage calculation are treated as two different components
* Mortgage rate calculation is done based on standard formula available online (https://www.jessym.com/articles/deriving-the-mortgage-payment-formula)
* loanValue is considered for the mortgage calculation and not home value
* maturity period and income is assumed to be in years.
* User is expected to input one of the available maturity period for caluclation. Else application is bound to return error
* maturity period is assumed to be unique
* No security framework is considered for this assessment

## What's inside

Project contains:
* application files
* required plugins and libraries
* In memory database and liquibase to setup data on application start up 
* docker file
* integration / unit test files
* jacoco report will be generated under target/site/jacoco/index.html

The application exposes only two default actuator endpoints (http://localhost:8080/actuator/health) and metrics endpoint
(http://localhost:8080/actuator/info).

## Setup

To run application in local run below command from project repo
```bash
  ./mvnw spring-boot:run
```
## Building and deploying the application

### Building the application

The project uses maven as a build tool. It already contains wrapper script, so there's no need to install maven.

To build the project execute the following command:

```bash
  ./mvnw clean install
```

If issue in above command then make sure to install maven on the your working machine and try below commands

```bash
  mvn clean install
  mvn spring-boot:run
```

### Running on Docker

Build the docker file on project repo

```bash
  docker build -t spring-boot-assessment .
```

Verify the list of images generated

```bash
  docker image ls
```
Launch docker container using 

```bash
  docker run -p 8080:8080 spring-boot-assessment
```

## Testing Application

Postman collection is added to the repo

## Scope for Improvements

* Having a distributed caching like redis to serve GET endpoint response
* Sort out jacoco file exclusions and improve code coverage
* Implement security framwork for user authentications etc