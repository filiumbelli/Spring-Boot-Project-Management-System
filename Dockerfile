FROM ubuntu:latest


RUN apt-get update && apt-get install -y openjdk-11-jdk

MAINTAINER sofisticat "serhatuyanmis@hotmail.com"
ENV version=docker
ENV dbuser=dbuser
ENV dbpass=dbuser
ENV jdbcurl=jdbc:postgresql://domain:port/postgres

WORKDIR /usr/local/bin

ADD management.jar .

ENTRYPOINT ["java","-jar","management.jar"]
