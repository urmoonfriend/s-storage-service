FROM openjdk:21
EXPOSE 8086
ADD target/storage.jar storage.jar
ENTRYPOINT ["java","-jar","/storage.jar"]
ENV TZ="Asia/Almaty"


