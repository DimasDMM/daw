FROM openjdk:8u121-jdk-alpine

ADD ./wait.sh /home/wait.sh

ADD ./images/ /home/images
COPY themadridtimes-0.0.1-SNAPSHOT.jar /home/themadridtimes.jar

EXPOSE 8080
CMD ["sh", "-c", "sh /home/wait.sh"]
