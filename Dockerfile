FROM maven:3.8-amazoncorretto-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM tomcat:10.0-jdk17-corretto
COPY --from=build /home/app/target/stupefy-subscription-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/
