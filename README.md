# A tutorial about Continuous Integration and Continuous Delivery by Dockerize Capstone Pipeline

This repository is a tutorial it tries to exemplify how to automatically manage the process of building, testing with the highest coverage, and deployment phases.

Our goal is to ensure our pipeline works well after each code being pushed. The processes we want to auto-manage:
* Code checkout
* Run tests
* Compile the code
* Run Sonarqube analysis on the code
* Create Docker image
* Push the image to Docker Hub
* Pull and run the image

## First step, running up the services

Since one of the goals is to obtain the ``sonarqube`` report of our project, we should be able to access sonarqube from the jenkins service. ``Docker compose`` is a best choice to run services working together. We configure our application services in a yaml file as below.

``docker-compose.yml``
```yml
version: '3.2'
services:
  sonarqube:
    build:
      context: sonarqube/
    ports:
      - 9000:9000
      - 9092:9092
    container_name: sonarqube
  jenkins:
    build:
      context: jenkins/
    privileged: true
    user: root
    ports:
      - 8080:8080
      - 50000:50000
    container_name: jenkins
    volumes:
      - /tmp/jenkins:/var/jenkins_home #Remember that, the tmp directory is designed to be wiped on system reboot.
      - /var/run/docker.sock:/var/run/docker.sock
    depends_on:
      - sonarqube
```
