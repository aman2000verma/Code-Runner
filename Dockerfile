FROM ubuntu

WORKDIR app

RUN apt update
RUN apt install -y npm nodejs
RUN apt install -y golang

#JAR file location (in IntelliJ Idea, JAR file created under out/artifacts directory)
ADD ./out/artifacts/runner/runner.jar ./

EXPOSE 8080

ENTRYPOINT ["java", "-jar" , "runner_jar.jar"]