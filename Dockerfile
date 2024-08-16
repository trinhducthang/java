FROM openjdk:22

ARG FILE_JAR=target/user-bank-manager-0.0.1-SNAPSHOT.jar

ADD ${FILE_JAR} api-service.jar

ENV SPRING_DATASOURCE_URL=jdbc:mysql://192.168.1.100:3306/test
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=123456789

ENTRYPOINT ["java", "-jar", "api-service.jar"]

EXPOSE 8080