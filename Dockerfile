#FROM openjdk:21
#EXPOSE 8086
#ADD target/storage.jar storage.jar
#ENTRYPOINT ["java","-jar","/storage.jar"]
#ENV TZ="Asia/Almaty"

FROM openjdk:21-jdk-slim
EXPOSE 8086 for storage service
ADD target/storage.jar storage.jar
ENTRYPOINT ["java", "-jar", "/storage.jar"]
ENV TZ="Asia/Almaty"
