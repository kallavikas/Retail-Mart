FROM openjdk:8-jdk-alpine

#RUN addgroup -S spring && adduser -S 10001 -G spring

#USER 10001:spring

WORKDIR /apps

COPY target/Jar_name.jar /apps/app.jar

ENV ms 4096m
ENV mx 5120m

EXPOSE 8080
#8081 management port
EXPOSE 8081

#Provide required permision
RUN chown -R 10001:spring /apps && chmod -R 777 /apps
USER 10001

ENTRYPOINT java -Xms${ms} -Xmx${mx} -jar /apps/app.jar