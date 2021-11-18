FROM openjdk:8
VOLUME /tmp
ADD ./target/spring-boot-apirest-matriz-0.0.1-SNAPSHOT.jar backend-apirest.jar
ENTRYPOINT ["java","-jar","/backend-apirest.jar"]