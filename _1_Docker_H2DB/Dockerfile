# Image Create
FROM openjdk:17

#ARG JAR_FILE=/target/SpringBoot_Ecommerce.jar
ARG JAR_FILE=*.jar

ADD ${JAR_FILE} ecommerce.jar

ENTRYPOINT ["java","-jar","/ecommerce.jar"]

# docker build -t imageName .
# docker build -f CustomerDockerfile -t imageName .
# docker image ls
# docker ps
# docker ps -a
# docker container run -d -p 5555:4444 --rm ecommerce
