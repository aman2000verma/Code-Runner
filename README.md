# Code-Runner
Application to run source code using various programming languages and run on Docker containers.

## How to run using Docker
- Generate JAR file for the Java source code
- Create a Docker image for the java file using Dockerfile (make sure to change the JAR file location in Dockerfile)
```
docker build -t runner .
```
- Create and run a new container on 8080 port
```
docker run -d -p 8080:8080 --name code_runner -v logs:/app/logs -v db:/app/db runner
```
- Now go to client folder and install Node Modules
```
npm install
```
- Run the app
```
npm start
```
![Screenshot](/ss.png)
