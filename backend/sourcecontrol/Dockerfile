FROM openjdk:17

ADD build/libs/*SNAPSHOT.jar sourcecontrol
ENTRYPOINT ["java", "-jar", "sourcecontrol"]
EXPOSE 8123