FROM openjdk:8
ADD target/Simple_Java.jar Simpl_Java-1.0-SNAPSHOT.jar
EXPOSE 8080