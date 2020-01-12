FROM maven:3.6.1-jdk-8 AS build  
RUN curl -sL https://deb.nodesource.com/setup_10.x | bash - \
  && apt-get install -y nodejs

RUN npm install -g @angular/cli

MAINTAINER Oksana Murdza

WORKDIR /parkhub
COPY . .

WORKDIR ./frontend
RUN npm ci

WORKDIR /parkhub
RUN mvn -f /parkhub/backend/pom.xml clean package

FROM openjdk:8-jre-alpine
COPY --from=build /parkhub/backend/target/backend-0.0.1-SNAPSHOT.jar /backend-0.0.1-SNAPSHOT.jar  
EXPOSE 8080  
ENTRYPOINT ["java","-jar","/backend-0.0.1-SNAPSHOT.jar"]





