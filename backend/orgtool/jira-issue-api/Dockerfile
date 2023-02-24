FROM openjdk:17

ADD build/libs/*SNAPSHOT.jar sourcecontrol
ENTRYPOINT ["java", "-jar", "jiraissueapi"]
EXPOSE 8223