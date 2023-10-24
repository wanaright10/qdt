FROM openjdk:8-jre-alpine
WORKDIR /jars
ADD qdt.jar qdt.jar
EXPOSE 8080
ENTRYPOINT ["java","-Dserver.port=8080","-jar","qdt.jar"]