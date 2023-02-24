FROM openjdk:17

ADD build/libs/*SNAPSHOT.jar sourcecontrol
ENTRYPOINT ["java", "-jar", "jiraissueservice"]
EXPOSE 8224