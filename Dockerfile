FROM openjdk:17-jdk

COPY target/emsp-server.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar" ,"emsp-server.jar"]