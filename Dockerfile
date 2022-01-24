FROM maven:3.3-jdk-8
WORKDIR /opt/app
COPY . .
ENTRYPOINT ["mvn", "clean", "compile", "exec:java"]

