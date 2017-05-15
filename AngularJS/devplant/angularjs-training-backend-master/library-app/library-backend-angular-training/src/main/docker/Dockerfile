FROM java:8-jre
VOLUME /tmp

ADD library-backend-angular-training.jar /app/

CMD ["java", "-Xmx750m", "-jar", "-Dspring.profiles.active=prod,sample-data", "/app/library-backend-angular-training.jar"]

EXPOSE 9000