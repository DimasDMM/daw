FROM openjdk:8u121-jdk-alpine

ADD wait.sh /wait.sh

COPY themadridnews-0.0.1-SNAPSHOT.jar /home/themadridnews.jar

EXPOSE 8080
CMD ["java", "-jar", "/home/themadridnews.jar"]
