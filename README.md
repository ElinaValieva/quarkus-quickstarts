# Quarkus QuickStart Project - Simple Blog 
![Java CI with Gradle](https://github.com/ElinaValieva/quarkus-blog-app/workflows/Java%20CI%20with%20Gradle/badge.svg?branch=master)
![Update Docker Hub Description](https://github.com/ElinaValieva/quarkus-quickstarts/workflows/Update%20Docker%20Hub%20Description/badge.svg)
> Simple blog api with Quarkus - the Supersonic Subatomic Java Framework. 
 
 Front-end application for this server: [UI for Blog](https://github.com/ElinaValieva/quarkus-quickstarts-blog-ui) :cyclone:

&nbsp;
## Prerequisites :heavy_exclamation_mark:
 1. `JDK 11` at least - for running app
 2. `Gradle 6+` - for building package
 3. `Docker` - for containerization
 4. `OpenShift Client` - for deploying app on OpenShift
 5. `AWS CLI`, `AWS SAM CLI` - for deploying AWS Lambda

&nbsp;
## Quarkus lifecycle :hammer:
#### Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```
./gradlew quarkusDev
```

#### Packaging and running the application
The application can be packaged using ./gradlew quarkusBuild. It produces the blog-1.0.0-SNAPSHOT-runner.jar file in the build directory.
```
./gradlew quarkusBuild

java -jar build/blog-1.0.0-SNAPSHOT-runner.jar
```

#### Creating a native executable
Using GraalVM:
```
./gradlew build -Dquarkus.package.type=native
```
Without GraalVM:
```
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
```
#### Docker run
```console
docker run -p 8090:8090 elvaliev/blog-api
```
#### OpenApi and Health Check
Application support open api - swagger and health check

&nbsp;
## Docker build :whale:
#### Build and run image manually
Application supports multiple Dockerfiles with different extentions `.jvm`, `.multistage` and `.native`. Extention `.jvm` used in `quarkus-container-image-docker` in packaging with next push to docker registry. 

Specify Dockerfile `native` or `multistage` build in command parameter with option `-f` and run image: 
```console
docker build -t blog-api -f src/main/docker/Dockerfile.multistage .

docker run -i --rm -p 8090:8090 blog-api
```
#### Push your image with Quarkus
Override parameters in `application.properties`:

|Properties|Description|
|--|--|
|**quarkus.container-image.name**|*Name of your image* [`application name`]|
|**quarkus.container-image.tag**|*Version* [`latest`]|
|**quarkus.container-image.registry**| *Docker registry* [`docker.io`]|
|**quarkus.container-image.username**| *Credentials*|
|**quarkus.container-image.password**| *Credentials*|
|**quarkus.container-image.push**| *Flag for pushing image* [`false`]|

&nbsp;

To push a container image for your project, quarkus.container-image.push=true needs to be set using any of the ways that Quarkus supports: 
```
./gradlew  quarkusBuild -Dquarkus.container-image.username=<USERNAME> \
                        -Dquarkus.container-image.password=<PASSWORD> \
                        -Dquarkus.container-image.push=true
```

&nbsp;
## Openshift deployment :triangular_flag_on_post:
Deployment using **s2i**:
```console
oc new-app quay.io/quarkus/ubi-quarkus-native-s2i:19.3.1-java11~https://github.com/ElinaValieva/quarkus-quickstarts.git \
              --context-dir=. --name=quarkus-blog-api
              
oc expose svc/quarkus-blog-api
```
Deployment using **docker image**:
```console
oc new-app elvaliev/blog-api:latest

oc expose svc/blog-api
```


&nbsp;
## AWS deployment :triangular_flag_on_post:
Enable property `awsLambdaEnabled = true` when you package `jar`
```
./gradlew -PawsLambdaEnabled=true quarkusBuild
```
For native build use template `build/sam.native.yaml`. 
```console
sam local start-api --template build/sam.jvm.yaml

sam package --template-file build/sam.jvm.yaml --output-template-file packaged.yaml --s3-bucket <YOUR_S3_BUCKET>

sam deploy --template-file packaged.yaml --capabilities CAPABILITY_IAM --stack-name <YOUR_STACK_NAME>
```

> To avoiding timeout error (502 - BAD GATEWAY) - increase `Timeout` in `build/sam.jvm.yaml`
